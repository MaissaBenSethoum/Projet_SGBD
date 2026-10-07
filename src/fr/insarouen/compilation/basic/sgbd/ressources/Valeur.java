package fr.insarouen.compilation.basic.sgbd.ressources;

import java.io.Serializable;
/**
 * Interface représentant une valeur stockée dans une cellule de la base de données.
 * 
 *
 * @author 
 * 
 */
public interface Valeur extends Serializable{   
    /**
     * Retourne le type SQL de cette valeur.
     *
     * @return Le type SQL correspondant à cette valeur.
     */    
    TypeSQL getType();
}