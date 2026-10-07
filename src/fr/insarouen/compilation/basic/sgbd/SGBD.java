package fr.insarouen.compilation.basic.sgbd;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.LinkedHashSet;
import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import fr.insarouen.compilation.basic.sgbd.ressources.*;
import fr.insarouen.compilation.basic.sgbd.conditions.*;

/**
 * Classe SGBD permettant de gérer un ensemble de tables.
 * Permet de sauvegarder et de charger une sauvegarde.
 * 
 *
 * @author 
 * 
 */
public class SGBD{
    /**
     * HashMap contenant les tables avec comme clé leur nom.
     */
    private Map<String,Table> tables;

    /**
     * Construit un nouveau SGBD.
     * Le monde est initialisé avec une HashMap vide.
     */
    public SGBD(){
        this.tables = new HashMap<String,Table>();
    }

    /**
     * Permet de créer une nouvelle table.
     * La nouvelle table est ajoutée à tables si elle n'existe pas.
     * 
     * @param nom le nom de la table.
     * @param attributs Liste contennant les attributs de la table
     */
    public void creerTable(String nom, Set<Attribut> attributs){
        if (nom == null || nom.isBlank()) {
            throw new NomTableInvalideException("Nom de table invalide");
        }
        if (this.tables.containsKey(nom)){
            throw new TableDejaExistanteException("> La table " + nom + " existe déjà");
        }
        this.tables.put(nom, new Table(nom, attributs));
    }
    /**
     * Permet de supprimer une table.
     * La table est retirée à tables si elle existe.
     * 
     * @param nom le nom de la table.
     */
    public void supprimerTable(String nom) {
        if (nom == null || nom.isBlank()) {
            throw new NomTableInvalideException("Nom de table invalide");
        }
        if (!this.tables.containsKey(nom)){
            throw new TableIntrouvableException("> La table " + nom + " est introuvable");
        }
        this.tables.remove(nom);
    }


    /**
     * Permet d'obtenir une Map contenant les tables avec comme clé leur nom
     */
    public Map<String,Table> getTables(){
        return this.tables;
    }

    /**
     * Affiche le nom des tables disponibles.
     * 
     */
    public void afficherTables(){
        if (this.tables.size() == 0){
            System.out.println("> Aucune table dans la base.");
        }else {
            System.out.println("> Tables disponibles : ");
            for (String nom : this.tables.keySet()) {
                System.out.printf("-%s",nom);
                System.out.println("");
            }
        }
    }

    /**
     * Permet d'insérer une ligne dans la table.
     * @param nomTable le nom de la table.
     * @param valeurs Liste des valeurs à ajouter dans la ligne
     */
    public void insertInto(String nomTable, List<Valeur> valeurs){
        if (nomTable == null || nomTable.isBlank()) {
            throw new NomTableInvalideException("Nom de table invalide");
        }
        if (valeurs == null) {
            throw new ValeurNulleException("Liste de valeurs nulle");
        }
        Table table = this.tables.get(nomTable);
        if (table == null){
            throw new TableIntrouvableException("> La table " + nomTable + " est introuvable");
        }
        table.insererLigne(valeurs);
    }

    /**
     * Permet de faire un SELECT sur une ou plusieurs tables.
     * Possibilité d'extraire des colonnes et de filtrer.
     * * @param colonnes Set des colonnes sélectionnées
     * @param nomsTables Set des noms des tables de la sélection
     * @param condition Condition de filtrage
     */
    public void select(Set<String> colonnes, Set<TableSource> nomsTables, Condition condition) {
        LinkedHashSet<String> nomsDesTables = new LinkedHashSet<String>();
        if (nomsTables == null || nomsTables.isEmpty()) {
            System.out.println("Erreur : Aucune table spécifiée dans le FROM.");
            return;
        }
        String nomTable, alias;
        Table table, tableAlias;
        List<String> tablesTemporaires = new ArrayList<String>();
        for (TableSource tableS : nomsTables) {
            nomTable = tableS.getNomTable();
            alias = tableS.getAlias();
            table = this.tables.get(nomTable);
            if (table == null) {
                throw new TableIntrouvableException("> La table " + nomTable + " est introuvable");
            }
            if (nomTable.equals(alias)){
                nomsDesTables.add(nomTable);
            } else {
                this.creerTable(alias, table.getAttributs());
                tableAlias = this.tables.get(alias);
                for (Ligne ligne : table.getLignes()) {
                    this.insertInto(alias, ligne.getValeurs());
                }
                nomsDesTables.add(alias);
                tablesTemporaires.add(alias);
            }
        }
        List<String> listeNomsTables = new ArrayList<String>(nomsDesTables);
        String nomTableCourante = listeNomsTables.get(0);
        try {
            for (int i = 1; i < listeNomsTables.size(); i++) {
                String nomTableSuivante = listeNomsTables.get(i);
                
                Table resultatPC = this.produitCartesien(nomTableCourante, nomTableSuivante);
                nomTableCourante = resultatPC.getNom();
                
                this.tables.put(nomTableCourante, resultatPC);
                tablesTemporaires.add(nomTableCourante);
            }

            if (condition != null) {
                Table resultatSelection = this.selection(nomTableCourante, condition);
                nomTableCourante = resultatSelection.getNom();
                
                this.tables.put(nomTableCourante, resultatSelection);
                tablesTemporaires.add(nomTableCourante);
            }

            if (colonnes.size() == 1 && colonnes.contains("*")) {
                System.out.println(this.tables.get(nomTableCourante).toString());
            } else {
                ArrayList<String> listeColonnes = new ArrayList<String>(colonnes);
                this.projection(listeColonnes, nomTableCourante);
            }

        } catch (StructureException e) {
            System.out.println("Erreur lors de l'exécution du SELECT : " + e.getMessage());
        } finally {
            for (String nomTemp : tablesTemporaires) {
                this.tables.remove(nomTemp);
            }
        }
    }

    /**
     * Permet de faire une selection sur une table.
     * Possibilité d'extraire des colonnes.
     * 
     * @param colonnes Liste des colonnes selectionnées
     * @param nomTable Nom de la table
     */
    public Table projection(List<String> colonnes, String nomTable){
    Table table = this.tables.get(nomTable);
    if (table == null) {
        throw new TableIntrouvableException("> La table " + nomTable + " est introuvable");
    }
    List<Ligne> lignes = table.getLignes();
    Set<Attribut> newAttributs = new LinkedHashSet<Attribut>();
    
    if (colonnes == null || colonnes.isEmpty()) {
        throw new ColonneInexistanteException("Aucune colonne sélectionnée");
    }
    for (String nomCol : colonnes){
        Attribut attribut = table.getAttribut(nomCol);
        if (attribut == null){
            throw new ColonneInexistanteException("> Colonne " + nomCol +" introuvable");
        }
        newAttributs.add(attribut);
    }
    Table newTable = new Table(table.getNom()+"_projection", newAttributs);

    for (Ligne ligne : lignes) {
        List<Valeur> newLigneValeurs = new ArrayList<Valeur>();
        for (String nomCol : colonnes) {
            int indexOrigine = -1;
            int curseur = 0;
            for (Attribut a : table.getAttributs()) {
                if (a.getNom().equals(nomCol)) {
                    indexOrigine = curseur;
                    break;
                }
                curseur++;
            }
            
            if (indexOrigine != -1) {
                newLigneValeurs.add(ligne.getValeur(indexOrigine));
            }
        }
        newTable.insererLigne(newLigneValeurs);
    }
    System.out.println(newTable.toString());
    return newTable;
}

        /**
     * Effectue le produit cartésien entre deux tables.
     * 
     * Le produit cartésien combine chaque ligne de la première
     * table avec chaque ligne de la seconde table.
     * 
     * La table résultat contient :
     * - tous les attributs de la première table
     * - suivis de tous les attributs de la seconde table
     * 
     * Le nom des attributs est préfixé par le nom de la table
     * afin d'éviter les conflits de noms.
     * 
     * @param nomTable1 le nom de la première table
     * @param nomTable2 le nom de la seconde table
     * @return une nouvelle table contenant le résultat du produit cartésien
     * @throws TableIntrouvableException levée si une des tables n'existe pas
     */
    public Table produitCartesien(String nomTable1, String nomTable2){
        Table t1 = this.tables.get(nomTable1);
        Table t2 = this.tables.get(nomTable2);
        if(t1 == null || t2 == null){
            throw new TableIntrouvableException("Table introuvable");
        }
        Set<Attribut> attributsResultat = new LinkedHashSet<Attribut>();
        for (Attribut a : t1.getAttributs()) {
            String nomAttribut = a.getNom();
            if (!nomAttribut.contains(".")) {
                nomAttribut = nomTable1 + "." + nomAttribut;
            }
            attributsResultat.add(new Attribut(nomAttribut, a.getType()));
        }

        for (Attribut a : t2.getAttributs()) {
            String nomAttribut = a.getNom();
            if (!nomAttribut.contains(".")) {
                nomAttribut = nomTable2 + "." + nomAttribut;
            }
            attributsResultat.add(new Attribut(nomAttribut, a.getType()));
        }
        Table resultat = new Table("produit-cartesien_" + nomTable1 +"_" + nomTable2, attributsResultat);
        for( Ligne l1 : t1.getLignes()){
            for( Ligne l2 : t2.getLignes()){
                List<Valeur> valeurs = new ArrayList<Valeur>();
                valeurs.addAll(l1.getValeurs());
                valeurs.addAll(l2.getValeurs());
                resultat.insererLigne(valeurs); 
            }
        }
        return resultat;
    }


    /**
     * Permet de supprimer des lignes selon des conditions d'une table.
     * 
     * @param nomTable le nom de la table.
     * @param condition Condition de filtrage
     */
    public void delete(String nomTable, Condition condition) {
        Table table = this.tables.get(nomTable);
        if (table == null){
            throw new TableIntrouvableException("> La table " + nomTable + " est introuvable");
        }
        table.delete(condition);
    }

    /**
     * Permet de selectionner des lignes sur une table selon une condition.
     * 
     */
    public Table selection(String nomTable, Condition condition){
        Table table = this.tables.get(nomTable);
        if(table == null){
            throw new TableIntrouvableException("Table introuvable");
        }
        Set<Attribut> attributs = new LinkedHashSet<Attribut>();
        attributs = table.getAttributs();
        Table resultat = new Table("selection_" + nomTable, attributs);
        for (Ligne l : table.getLignes()){
            if (condition.evaluer(l,attributs)){
                List<Valeur> valeurs = new ArrayList<Valeur>();
                valeurs.addAll(l.getValeurs());
                resultat.insererLigne(valeurs);
            }
        }
        return resultat;
    }

    /**
     * Permet de sauvegarder l'ensemble des tables dans un fichier save.
     * 
     */
    public void sauvegarder() throws IOException{
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save"));
        oos.writeObject(this.tables);
        oos.close();
    }

    /**
     * Permet de charger l'ensemble des tables dans un fichier save.
     * 
     */
    public void repriseSession() throws IOException, ClassNotFoundException{
        Path chemin = Paths.get("save");
        if (Files.exists(chemin)){
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save"));
            this.tables = (HashMap<String,Table>)ois.readObject();
            ois.close();
        }
    }

}