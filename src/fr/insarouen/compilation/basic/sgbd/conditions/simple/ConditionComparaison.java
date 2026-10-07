package fr.insarouen.compilation.basic.sgbd.conditions.simple;

import java.util.Set;
import fr.insarouen.compilation.basic.sgbd.Ligne;
import fr.insarouen.compilation.basic.sgbd.ressources.Valeur;
import fr.insarouen.compilation.basic.sgbd.ressources.IntValeur;
import fr.insarouen.compilation.basic.sgbd.ressources.VarcharValeur;
import fr.insarouen.compilation.basic.sgbd.ressources.SerialValeur;
import fr.insarouen.compilation.basic.sgbd.ressources.Attribut;
import fr.insarouen.compilation.basic.sgbd.conditions.Condition;
import fr.insarouen.compilation.basic.sgbd.ConditionInvalideException;


/**
 *  Classe représentant une condition de comparaison simple.
 *  Cette condition compare une colonne avec une valeur
 *  ou un autre objet à l’aide d’un opérateur.
 * 
 */
public class ConditionComparaison implements Condition {
/**
 * Colonne à comparer.
 */
    private String nomCol;
/**
 *  Opérateur de comparaison utilisé.
 */
    private String op;
/**
 * Objet à comparer, peut être soit une colonne soit un objet de type Valeur.
 */
    private Object valeur;
/**
 * Construit la nouvelle condition.
 */
    public ConditionComparaison(String nomCol, String op, Object valeur){
        if (nomCol == null || nomCol.isBlank()) {
            throw new ConditionInvalideException("Nom de colonne invalide");
        }
        if (op == null) {
            throw new ConditionInvalideException("Opérateur nul");
        }
        if (!op.equals("=") && !op.equals(">")&& !op.equals("<")) {
            throw new ConditionInvalideException("Opérateur de comparaison invalide");
        }
        if (valeur == null) {
            throw new ConditionInvalideException("Valeur de comparaison nulle");
        }
        this.nomCol = nomCol;
        this.op = op;
        this.valeur = valeur;
    }
/**
 * Permet de vérifier si la condition est valide pour une ligne.
 * 
 * @return boolean
 */
    @Override 
    public boolean evaluer(Ligne ligne, Set<Attribut> attributs){
        Valeur valeurGauche = valeurLigne(this.nomCol, ligne, attributs);

        Valeur valeurDroite;
        if (this.valeur instanceof Valeur) {
            valeurDroite = (Valeur)this.valeur;
        } else if (this.valeur instanceof String) {
            valeurDroite = valeurLigne((String)this.valeur, ligne, attributs);
        } else {
            return false;
        }
        return comparer(valeurGauche, valeurDroite, this.op);
    }
/**
 * Permet d'obtenir la valeur de la ligne correspondant à la colonne.
 * 
 * @return Valeur
 */
    public Valeur valeurLigne(String nomCol, Ligne ligne, Set<Attribut> attributs){
        int i = 0;
        for ( Attribut attr: attributs){
            if (attr.getNom().equals(nomCol)){
                return ligne.getValeur(i);
            }
            i += 1;
        }
        throw new ConditionInvalideException("Colonne " + nomCol + " introuvable");
    }
/**
 * Permet de comparer les valeurs.
 * 
 * @return boolean
 */
    public boolean comparer(Valeur valeurGauche, Valeur valeurDroite, String operateur){

        if (( valeurGauche instanceof IntValeur) && (valeurDroite instanceof IntValeur)){
            int gauche = ((IntValeur) valeurGauche).getDonnee();
            int droite = ((IntValeur) valeurDroite).getDonnee();
            switch (operateur) {
                case "=": return (gauche == droite);
                case ">": return (gauche > droite);
                case "<": return (gauche < droite);
                default: return false;
            }
        }

        if (( valeurGauche instanceof SerialValeur) && (valeurDroite instanceof SerialValeur)){
            int gauche = ((SerialValeur) valeurGauche).getDonnee();
            int droite = ((SerialValeur) valeurDroite).getDonnee();
            switch (operateur) {
                case "=": return (gauche == droite);
                case ">": return (gauche > droite);
                case "<": return (gauche < droite);
                default: return false;
            }
        }

        if (( valeurGauche instanceof IntValeur) && (valeurDroite instanceof SerialValeur)){
            int gauche = ((IntValeur) valeurGauche).getDonnee();
            int droite = ((SerialValeur) valeurDroite).getDonnee();
            switch (operateur) {
                case "=": return (gauche == droite);
                case ">": return (gauche > droite);
                case "<": return (gauche < droite);
                default: return false;
            }
        }

        if (( valeurGauche instanceof SerialValeur) && (valeurDroite instanceof IntValeur)){
            int gauche = ((SerialValeur) valeurGauche).getDonnee();
            int droite = ((IntValeur) valeurDroite).getDonnee();
            switch (operateur) {
                case "=": return (gauche == droite);
                case ">": return (gauche > droite);
                case "<": return (gauche < droite);
                default: return false;
            }
        }

        if ((valeurGauche instanceof VarcharValeur) && (valeurDroite instanceof VarcharValeur)){
            String gauche = ((VarcharValeur) valeurGauche).getDonnee();
            String droite = ((VarcharValeur) valeurDroite).getDonnee();
            switch (operateur) {
                case "=": return (gauche.equals(droite));
                case ">": return (gauche.compareTo(droite) > 0);
                case "<": return (gauche.compareTo(droite) < 0);
                default: return false;
            }
        }   
        System.out.printf(String.format("Erreur: Types incompatibles pour la comparaison : %s et %s \n",valeurGauche.getType(),valeurDroite.getType()));
        return false;
    }

}