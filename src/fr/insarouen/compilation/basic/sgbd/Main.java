package fr.insarouen.compilation.basic.sgbd;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.io.IOException;
import java.lang.ClassNotFoundException;
import fr.insarouen.compilation.basic.sgbd.ressources.*;
import fr.insarouen.compilation.basic.sgbd.conditions.*;
import fr.insarouen.compilation.basic.sgbd.conditions.simple.ConditionComparaison;

public class Main {
    public static void main(String[] args)  throws IOException, ClassNotFoundException{
        Set<Attribut> attributs = new LinkedHashSet<Attribut>();
        Attribut age, nom;
        age = new Attribut("age",TypeSQL.INT);
        nom = new Attribut("nom", TypeSQL.VARCHAR);
        attributs.add(age);
        attributs.add(nom);

        Set<Attribut> attributs2 = new LinkedHashSet<Attribut>();
        Attribut yage = new Attribut("Taille",TypeSQL.INT);
        Attribut ynom = new Attribut("Prenom", TypeSQL.VARCHAR);
        attributs2.add(yage);
        attributs2.add(ynom);

        SGBD sgbd = new SGBD();
        sgbd.creerTable("Ewen",attributs);
        sgbd.creerTable("Yesmina",attributs2);
        sgbd.afficherTables();
        System.out.println(sgbd.getTables().get("Ewen").toString());
        //sgbd.sauvegarder();
        IntValeur age1 = new IntValeur(10);
        VarcharValeur nom1 = new VarcharValeur("EwenJr");
        List<Valeur> ligne = new ArrayList<Valeur>();
        ligne.add(age1);
        ligne.add(nom1);
        sgbd.insertInto("Ewen", ligne);

        IntValeur age2 = new IntValeur(5);
        VarcharValeur nom2 = new VarcharValeur("Yesmina2");
        List<Valeur> ligne2 = new ArrayList<Valeur>();
        ligne2.add(age2);
        ligne2.add(nom2);
        sgbd.insertInto("Yesmina", ligne2);

        IntValeur age3 = new IntValeur(8);
        VarcharValeur nom3 = new VarcharValeur("EwenJrJr");
        List<Valeur> ligne3 = new ArrayList<Valeur>();
        ligne3.add(age3);
        ligne3.add(nom3);
        sgbd.insertInto("Ewen", ligne3);

        IntValeur age4 = new IntValeur(50);
        VarcharValeur nom4 = new VarcharValeur("Maissa");
        List<Valeur> ligne4 = new ArrayList<Valeur>();
        ligne4.add(age4);
        ligne4.add(nom4);
        sgbd.insertInto("Yesmina", ligne4);

        System.out.println(sgbd.getTables().get("Ewen").toString());
        //sgbd.supprimerTable("Ewen");
        sgbd.produitCartesien("Ewen","Yesmina");
        List<String> colonnes = new ArrayList<String>();
        colonnes.add("age");
        VarcharValeur val = new VarcharValeur("Maissa");
        ConditionComparaison condition = new ConditionComparaison("Prenom","=",val);
        sgbd.projection(colonnes,"Ewen");
        //sgbd.delete("Ewen",condition);
        System.out.println(sgbd.selection("Yesmina",condition).toString());
        System.out.println(sgbd.getTables().get("Ewen").toString());
        sgbd.afficherTables();
        sgbd.sauvegarder();
        sgbd.repriseSession();
        sgbd.afficherTables();
    }
}