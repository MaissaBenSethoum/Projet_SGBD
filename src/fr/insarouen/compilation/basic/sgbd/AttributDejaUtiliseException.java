package fr.insarouen.compilation.basic.sgbd;


/**
 * Exception levée lorsque l'attribut à déjà été utilisé.
 */
public class AttributDejaUtiliseException extends StructureException{

    /**
     * Constructeur avec message.
     */
    public AttributDejaUtiliseException(String message) {
        super(message);
    }

    /**
     * Constructeur avec message et cause.
     */
    public AttributDejaUtiliseException(String message, Throwable cause) {
        super(message, cause);
    }
}