package fr.insarouen.compilation.basic.sgbd;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.io.Serializable;

import fr.insarouen.compilation.basic.sgbd.ressources.Attribut;
import fr.insarouen.compilation.basic.sgbd.ressources.*;
import fr.insarouen.compilation.basic.sgbd.conditions.*;

public class Table implements Serializable {
    
    // Attributs

    private String nom;
    private Set<Attribut> attributs;
    private List<Ligne> lignes;
    private static int nextSerialId = 1;


    // Constructeurs

    /**
     * Constructeur de table à partir d'un nom et d'une ArrayList d'Attributs.
     * Aucune Ligne n'est présente dans la table à l'initialisation.
     *
     * @param nom Le nom de la table (ex: "students", "grades").
     * @param attributs Une ArrayList d'Attributs.
     */
    public Table(String nom, Set<Attribut> attributs){
        if (nom == null || nom.isBlank()) {
            throw new AttributInvalideException("Nom de table invalide");
        }
        if (attributs == null) {
            throw new ValeurNulleException("Liste d'attributs nulle");
        }
        if (attributs.isEmpty()) {
            throw new AttributInvalideException("Une table doit contenir au moins un attribut");
        }
        this.nom = nom;
        this.attributs = new LinkedHashSet<>(attributs);
        for (Attribut a : this.attributs) {
            if (getAttribut(a.getNom(), 2)) {
                throw new AttributDejaUtiliseException("Erreur : 2 attributs ont le même nom");
            }
        }
        this.lignes = new ArrayList<Ligne>();
    }


    // Méthodes

    /**
     * Insérer une nouvelle ligne à la fin de l'ArrayList de Ligne de l'instance.
     * 
     * @param valeurs L'ArrayList<Valeur> permettant l'appel du constructeur de Ligne.
     */
    public void insererLigne(List<Valeur> valeurs){
        if (valeurs == null) {
            throw new ValeurNulleException("La liste des valeurs est nulle");
        }
        for (Valeur valeur : valeurs) {
            if (valeur == null) {
                throw new ValeurNulleException("Une valeur de la ligne est nulle");
            }
        }
        Boolean estAuFormat = true;
        int longueur = this.attributs.size();
        if (((LinkedHashSet<Attribut>)this.attributs).getFirst().getType()==TypeSQL.SERIAL){
            if ((valeurs.size() != longueur)){
                Valeur serial = new SerialValeur(genererProchainSerial());
                valeurs.addFirst(serial);
            }
        }
        if ((valeurs.size() == longueur)) {
            int i =0;
            for (Attribut attribut : this.attributs) {
                if (valeurs.get(i).getType() != (attribut.getType())) {
                    estAuFormat = false;
                }
                i++;
            }
        }
        else {
            estAuFormat = false;
        }

        if (estAuFormat) {
            Ligne nvLigne = new Ligne(valeurs);
            this.lignes.add(nvLigne);
        }
        else {
            throw new DonneesInvalidesException("La ligne a inserer n'est pas ne respecte pas la Table.");
        }
    }

    /**
     * Permet de supprimer une ligne selon une condition.
     * 
     */
    public void delete(Condition condition) {
        List<Ligne> lignesSup = new ArrayList<Ligne>();
        for (Ligne ligne : this.lignes){
            if (condition != null){
                if (condition.evaluer(ligne,this.attributs)){
                    lignesSup.add(ligne);
                }
            }else{
                lignesSup.add(ligne);
            }
        }

        for (Ligne ligne : lignesSup){
            this.lignes.remove(ligne);
        }
    }

    /**
     * Getter classique d'attributs.
     * 
     * @return Renvoie la LinkedHashList de l'instance. N.B. Méthode .iterator() possible.
     */

    public Set<Attribut> getAttributs() {
        return this.attributs;
    }

    /**
     * Parcourt la liste d'attributs et renvoit l'attribut correspondant au nom.
     * 
     * @param nomColonne Nom de l'attribut recherché<;
     * @return La première instance d'attribut correspondant au nom donné.
     */
    public Attribut getAttribut(String nomColonne) {
        Attribut res = null;
        Attribut temp;
        Iterator<Attribut> i = this.attributs.iterator();
        while (i.hasNext() && res==null) {
            temp = (Attribut)i.next();
            if (temp.getNom().equals((String)nomColonne)) {
                res = temp;
            }
        }
        return res;     //Se méfier du cas où on a parcouru toute la liste d'attributs et qu'on n'a pas trouvé de correspondance (res = null en fin de boucle) 
    }

    /**
     * Surcharge de la méthode ci-dessus pour tester l'exception AttributDejaUtilise dans le constructeur.
     * 
     * @param nomColonne Nom de l'attribut recherché;
     * @param nbApparition Nombre de fois qu'on veut voir apparaître l'attribut.
     * @return Vrai si l'attribut demandé est présent au moins nbApparition fois dans la liste d'attribut.
     */
    public Boolean getAttribut(String nomColonne, int nbApparition) {
        Boolean res = false;
        Attribut temp;
        Iterator<Attribut> i = this.attributs.iterator();
        while (i.hasNext() && res==null) {
            temp = (Attribut)i.next();
            if (temp.getNom().equals((String)nomColonne)) {
                nbApparition = nbApparition - 1;
            }
            if (nbApparition <= 0) {
                res = true;
            }
        }
        return res;
    }


    /**
     * Gestion de l'auto-incrémentation pour le type Serial.
     * 
     * @return Le serialId courant.
     */
    public int genererProchainSerial() {
        int res = this.nextSerialId;
        this.nextSerialId = this.nextSerialId + 1;
        return res;
    }

    /**
     * Renvoie une liste des lignes de la table
     * 
     * @return List<Ligne> 
     */
    public List<Ligne> getLignes() {
        return this.lignes;
    }

    /**
     * Renvoie le nom de la Table
     * @return nom de la table
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Méthode de toString formatée pour afficher la table comme souhaité.
     * Utilise String.format pour garantir un espacement propre.
     * * @return Un String formalisé de l'instance de table.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int constanteEspace = 20; 

        sb.append(String.format("> Table: %s\n", this.nom));

        for (Attribut attribut : this.attributs) {
            sb.append(String.format("| %-20s", attribut.getNom()));
        }
        sb.append("|\n");

        for (int i = 0; i < this.attributs.size(); i++) {
            sb.append(String.format("|%s", "-".repeat(constanteEspace + 1)));
        }
        sb.append("|\n");

        if (this.lignes != null) {
            for (Ligne ligne : this.lignes) {
                for (int iterValeur = 0; iterValeur < this.attributs.size(); iterValeur++) {
                    String valeurCellule = ligne.getValeur(iterValeur).toString();
                    sb.append(String.format("| %-20s", valeurCellule));
                }
                sb.append("|\n");
            }
        }

        return sb.toString();
    }
}