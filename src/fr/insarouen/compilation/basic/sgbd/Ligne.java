package fr.insarouen.compilation.basic.sgbd;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import fr.insarouen.compilation.basic.sgbd.ressources.*;

/**
 * Classe Ligne permettant de gérer les lignes d'une table.
 * 
 *
 * @author 
 * 
 */
public class Ligne implements Serializable {
    /**
     * ArrayList contenant des valeurs.
     */
    private List<Valeur> valeurs;
    /**
     * Construit une nouvelle ligne.
     */
    public Ligne(List<Valeur> valeurs) throws ValeurNulleException {
    if (valeurs == null) {
        throw new ValeurNulleException("La liste de valeurs ne doit pas être nulle");
    }

    for (Valeur valeur : valeurs) {
        if (valeur == null) {
            throw new ValeurNulleException("Une valeur de la ligne ne doit pas être nulle");
        }
    }

    this.valeurs = new ArrayList<>(valeurs);
    }
    
    /**
     * Parcourt la liste de valeurs et renvoit la valeur correspondant a l'indice.
     * 
     * @param indice Indice de la valeur recherché;
     * @return La valeur correspondant à l'indice donné.
     */
    public Valeur getValeur(int indice) throws IndexHorsLimiteException {
    if (indice < 0 || indice >= this.valeurs.size()) {
        throw new IndexHorsLimiteException("Indice hors limites");
    }
    return this.valeurs.get(indice);
    }

    /**
     * Parcourt la liste de valeurs et renvoit toutes les valeurs de la ligne.
     * 
     * @return La Liste des Valeurs de la ligne.
     */
    public List<Valeur> getValeurs(){
        return this.valeurs;
    }

    /**
     * Parcourt la liste de valeurs et modifie la valeur à l'indice correspondant.
     * 
     * @param indice Indice de la valeur recherché;
     * @param valeur Nouvelle valeur.
     * 
     */
    public void setValeurs(int indice ,Valeur valeur) throws ValeurNulleException,IndexHorsLimiteException {
        if (indice < 0 || indice >= this.valeurs.size()) {
            throw new IndexHorsLimiteException("Indice hors limites");
        }
        if (valeur == null) {
            throw new ValeurNulleException("La valeur ne doit pas etre null");
        }
        this.valeurs.set(indice,valeur);
    }

    @Override
    public String toString(){
        return this.valeurs.toString();
    }
}