package fr.insarouen.compilation.basic.sgbd;

public class TableSource {
/**
 * Stocke le nom de la table.
 *
 */
    private String nomTable;
/**
 * Stocke l'alias de la table.
 *
 */
    private String alias;
/**
 * Contructeur de TableSource.
 *
 */
    public TableSource(String n, String a) {
        if (n == null || n.isBlank()) {
            throw new NomTableInvalideException("Le nom de la table ne peut pas être vide");
        }
        this.nomTable = n; 
        this.alias = a;
    }
/**
 * Renvoie le nom de la table.
 *
 * @return String
 */
    public String getNomTable() {
        return this.nomTable;
    }
/**
 * Renvoie le nom de l'alias.
 *
 * @return String
 */
    public String getAlias() {
        return this.alias;
    }

}