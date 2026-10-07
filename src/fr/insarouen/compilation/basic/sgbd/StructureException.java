package fr.insarouen.compilation.basic.sgbd;

/**
 * Exception liée à la structure du SGBD.
 */
public class StructureException extends SGBDException {

    /**
     * Constructeur avec message.
     */
    public StructureException(String message) {
        super(message);
    }

    /**
     * Constructeur avec message et cause.
     */
    public StructureException(String message, Throwable cause) {
        super(message, cause);
    }
}