package fr.insarouen.compilation.basic.sgbd;

/**
 * Exception levée lorsqu’une table est introuvable.
 */
public class TableIntrouvableException extends StructureException {

    /**
     * Constructeur avec message.
     */
    public TableIntrouvableException(String message) {
        super(message);
    }

    /**
     * Constructeur avec message et cause.
     */
    public TableIntrouvableException(String message, Throwable cause) {
        super(message, cause);
    }
}