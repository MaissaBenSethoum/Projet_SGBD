package fr.insarouen.compilation.basic.sgbd.ressources;

import java.io.Serializable;
import fr.insarouen.compilation.basic.sgbd.AttributInvalideException;

/**
 * Classe Attribut permettant de gérer les attributs sous forme de Record.
 */
public record Attribut(String nom,TypeSQL type)implements Serializable{

    public Attribut{
        
    if (nom == null || nom.isBlank()){
        throw new AttributInvalideException("Nom d'attribut invalide");
    }

    if (type == null){
        throw new AttributInvalideException("Type d'attribut invalide");
    }
    }
    /**
     * Getter pour le nom .
     */
    public String getNom(){return nom;}

    /**
     * Getter pour le type .
     */
    public TypeSQL getType(){return type;}
}