package fr.insarouen.compilation.basic.sgbd;

/**
 * Exception levée lorsque le nom de la table est invalide.
 */
public class NomTableInvalideException extends StructureException {

    /**
     * Constructeur avec message.
     */
    public NomTableInvalideException(String message) {
        super(message);
    }

    /**
     * Constructeur avec message et cause.
     */
    public NomTableInvalideException(String message, Throwable cause) {
        super(message, cause);
    }
}