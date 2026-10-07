package fr.insarouen.compilation.basic.sgbd.ressources;
/**
 * Représente une valeur de type SERIAL dans le SGBD.
 * Encapsule un entier Java int géré par une table et retourne
 * SERIAL comme type SQL.
 */
public class SerialValeur implements Valeur{
/** La donnée entière auto-incrémentée. */
    private int donnee;
/**
 * Construit une nouvelle valeur série.
 *
 * @param donnee La valeur entière auto-incrémentée à stocker.
 */
    public SerialValeur(int donnee){
        this.donnee = donnee;
    }

/**
 * Renvoie la valeur de la donnee
 *
 * @return int
 */
    public int getDonnee(){
        return this.donnee;
    }
/**
 * renvoie le type sql SERIAL.
 * @return TypeSQL.SERIAL.
 */
    @Override
    public TypeSQL getType(){
        return TypeSQL.SERIAL;
    }
    
    public String toString(){
        return String.valueOf(this.donnee);
    }
}
