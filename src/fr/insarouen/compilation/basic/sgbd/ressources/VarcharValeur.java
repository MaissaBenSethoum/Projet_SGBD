package fr.insarouen.compilation.basic.sgbd.ressources;
import fr.insarouen.compilation.basic.sgbd.ValeurNulleException;
/**
 * Représente une valeur de type VARCHAR dans le SGBD.
 * Encapsule une chaîne de caractères Java String
 * et retourne VARCHAR comme type SQL.
 */
public class VarcharValeur implements Valeur{
/** La chaîne de caractères stockée. */ 
    private String donnee;
/**
 * Construit une nouvelle valeur VARCHAR.
 *
 * @param donnee La chaîne de caractères à stocker.
 */
    public VarcharValeur(String donnee){
        if (donnee == null){
            throw new ValeurNulleException("Une valeur VARCHAR ne peut pas être nulle");
        }
        this.donnee = donnee;
    }
/**
 * Renvoie la valeur de la donnee
 *
 * @param donnee La chaîne de caractères à stocker.
 */
    public String getDonnee(){
        return this.donnee;
    }
/**
 *
 * @return TypeSQL.VARCHAR.
 */
    @Override
    public TypeSQL getType(){
        return TypeSQL.VARCHAR;
    }
    
    public String toString(){
        return this.donnee.toString();
    }
}
