package fr.insarouen.compilation.basic.sgbd.conditions.composee;

import fr.insarouen.compilation.basic.sgbd.ressources.Attribut;
import fr.insarouen.compilation.basic.sgbd.conditions.Condition;
import fr.insarouen.compilation.basic.sgbd.Ligne;
import fr.insarouen.compilation.basic.sgbd.ConditionInvalideException;
import java.util.Set;
/**
 * Classe ConditionEt qui permet de stocker les conditions liées par un ET.
 */
public class ConditionOu implements Condition {
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
    public ConditionOu(Condition g, Condition d){
        if (g == null || d == null){
            throw new ConditionInvalideException("Les conditions d'un OU ne peuvent pas être nulles");
        }
        this.gauche = g;
        this.droite = d;
    }
/**
 * Permet de vérifier si la condition est valide dans une ligne.
 */
    @Override 
    public boolean evaluer(Ligne ligne, Set<Attribut> attributs){
        return (this.gauche.evaluer(ligne, attributs) || this.droite.evaluer(ligne, attributs));
    }

}