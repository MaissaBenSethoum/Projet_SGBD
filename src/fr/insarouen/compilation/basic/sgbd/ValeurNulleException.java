package fr.insarouen.compilation.basic.sgbd;

/**
 * Exception levée en cas de Valeur Nulle.
 */
public class ValeurNulleException extends DonneesInvalidesException{
    /**
     * Constructeur avec message.
     */
    public ValeurNulleException(String message) {
        super(message);
    }
    
    /**
     * Constructeur avec message et cause.
     */
    public ValeurNulleException(String message, Throwable cause) {
        super(message, cause);
    }
}