package fr.insarouen.compilation.basic.sgbd;
/**
 * Exception levée en cas d'attribut invalide.
 */
public class AttributInvalideException extends StructureException{
    /**
     * Constructeur avec message.
     */
    public AttributInvalideException(String message){
        super(message);
    }

    /**
     * Constructeur avec message et cause.
     */
    public AttributInvalideException(String message, Throwable cause) {
        super(message, cause);
    }
}