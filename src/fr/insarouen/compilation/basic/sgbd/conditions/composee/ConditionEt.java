package fr.insarouen.compilation.basic.sgbd.conditions.composee;

import java.util.Set;
import fr.insarouen.compilation.basic.sgbd.Ligne;
import fr.insarouen.compilation.basic.sgbd.ressources.Attribut;
import fr.insarouen.compilation.basic.sgbd.conditions.simple.ConditionComparaison;
import fr.insarouen.compilation.basic.sgbd.conditions.Condition;
import fr.insarouen.compilation.basic.sgbd.ConditionInvalideException;

/**
 * Classe ConditionEt qui permet de stocker les conditions liées par un ET.
 */
public class ConditionEt implements Condition {
/**
 * Stocke la condition de gauche.
 */
    private Condition gauche;
/**
 * Stocke la condition de droite.
 */
    private Condition droite;
/**
 * Créer la condition.
 */
    public ConditionEt(Condition g, Condition d){
        if (g == null || d == null){
            throw new ConditionInvalideException("Les conditions d'un ET ne peuvent pas être nulles");
        }
        this.gauche = g;
        this.droite = d;
    }
/**
 * Permet de vérifier si la condition est valide pour une ligne.
 */
    @Override 
    public boolean evaluer(Ligne ligne, Set<Attribut> attributs){
        return (this.gauche.evaluer(ligne, attributs) && this.droite.evaluer(ligne, attributs));
    }
}