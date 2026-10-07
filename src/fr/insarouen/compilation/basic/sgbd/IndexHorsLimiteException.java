package fr.insarouen.compilation.basic.sgbd;

/**
 * Exception levée lorsqu’un index est hors limites.
 */
public class IndexHorsLimiteException extends StructureException {

    /**
     * Constructeur avec message.
     */
    public IndexHorsLimiteException(String message) {
        super(message);
    }

    /**
     * Constructeur avec message et cause.
     */
    public IndexHorsLimiteException(String message, Throwable cause) {
        super(message, cause);
    }
}