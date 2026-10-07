package fr.insarouen.compilation.basic.sgbd;

/**
 * Exception levée lorsqu’une ligne existe déjà.
 */
public class LigneDejaExistanteException extends StructureException {

    /**
     * Constructeur avec message.
     */
    public LigneDejaExistanteException(String message) {
        super(message);
    }

    /**
     * Constructeur avec message et cause.
     */
    public LigneDejaExistanteException(String message, Throwable cause) {
        super(message, cause);
    }
}