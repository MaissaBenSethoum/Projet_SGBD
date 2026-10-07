package fr.insarouen.compilation.basic.sgbd;
/**
 * Exception levée en cas de condition invalide.
 */
public class ConditionInvalideException extends StructureException {
    /**
     * Constructeur avec message.
     */
    public ConditionInvalideException(String message){
        super(message);
    }

    /**
     * Constructeur avec message et cause.
     */
    public ConditionInvalideException(String message, Throwable cause) {
        super(message, cause);
    }
}