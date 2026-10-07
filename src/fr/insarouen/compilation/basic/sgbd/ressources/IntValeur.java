package fr.insarouen.compilation.basic.sgbd.ressources;
/**
 * Représente une valeur de type INT dans le SGBD.
 * Encapsule un entier Java int et retourne
 * INT comme type SQL.
 */
public class IntValeur implements Valeur{
/** La donnée entière stockée. */
    private int donnee;
/**
 * Construit une nouvelle valeur entière.
 *
 * @param donnee La valeur entière à stocker.
 */
    public IntValeur(int donnee){
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
 * Renvoie le type sql INT.
 * @return TypeSQL.INT.
 */
    @Override
    public TypeSQL getType(){
        return TypeSQL.INT;
    }
    
    public String toString(){
        return String.valueOf(this.donnee);
    }
}
