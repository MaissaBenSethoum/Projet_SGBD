package fr.insarouen.compilation.basic.sgbd.conditions;

import fr.insarouen.compilation.basic.sgbd.ressources.Attribut;
import fr.insarouen.compilation.basic.sgbd.Ligne;
import java.util.Set;

/**
 * Interface qui permet de récupérer les conditions à partir du parseur.
 *  
 */
public interface Condition {
    
/**
 * Permet de vérifier si la condition est valide pour une ligne.
 */
    boolean evaluer(Ligne ligne, Set<Attribut> attributs);

}