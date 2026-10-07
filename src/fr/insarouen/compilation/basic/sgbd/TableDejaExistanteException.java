package fr.insarouen.compilation.basic.sgbd;

/**
 * Exception levée lorsqu’une table existe déjà.
 */
public class TableDejaExistanteException extends StructureException {

    /**
     * Constructeur avec message.
     */
    public TableDejaExistanteException(String message) {
        super(message);
    }

    /**
     * Constructeur avec message et cause.
     */
    public TableDejaExistanteException(String message, Throwable cause) {
        super(message, cause);
    }
}