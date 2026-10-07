package fr.insarouen.compilation.basic.sgbd.ressources;
/**
 * Énumération des types SQL supportés par le SGBD.
 * Chaque type correspond à un format de donnée pouvant être
 * stocké dans un Attribut de table.
 */

public enum TypeSQL{
/** Type entier. */
    INT,
/** Type série. */
    SERIAL,
/** Type chaîne de caractères. */
    VARCHAR
}