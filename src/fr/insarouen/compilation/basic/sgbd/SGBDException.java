package fr.insarouen.compilation.basic.sgbd;

/**
 * Exception générale du SGBD.
 */
public class SGBDException extends RuntimeException{

    /**
     * Constructeur avec message.
     */
    public SGBDException(String message) {
        super(message);
    }

    /**
     * Constructeur avec message et cause.
     */
    public SGBDException(String message, Throwable cause) {
        super(message, cause);
    }
}