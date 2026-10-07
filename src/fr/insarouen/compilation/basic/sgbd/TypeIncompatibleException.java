package fr.insarouen.compilation.basic.sgbd;

/**
 * Exception levée en cas de type incompatible.
 */
public class TypeIncompatibleException extends DonneesInvalidesException {

    /**
     * Constructeur avec message.
     */
    public TypeIncompatibleException(String message) {
        super(message);
    }

    /**
     * Constructeur avec message et cause.
     */
    public TypeIncompatibleException(String message, Throwable cause) {
        super(message, cause);
    }
}