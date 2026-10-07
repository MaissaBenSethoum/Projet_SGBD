package fr.insarouen.compilation.basic.sgbd;

/**
 * Exception levée lorsque des données sont invalides.
 */
public class DonneesInvalidesException extends SGBDException {

    /**
     * Constructeur avec message.
     */
    public DonneesInvalidesException(String message) {
        super(message);
    }

    /**
     * Constructeur avec message et cause.
     */
    public DonneesInvalidesException(String message, Throwable cause) {
        super(message, cause);
    }
}