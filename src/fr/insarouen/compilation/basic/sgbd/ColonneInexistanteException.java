package fr.insarouen.compilation.basic.sgbd;

/**
 * Exception levée lorsqu’une colonne est introuvable.
 */
public class ColonneInexistanteException extends StructureException{

    /**
     * Constructeur avec message.
     */
    public ColonneInexistanteException(String message) {
        super(message);
    }

    /**
     * Constructeur avec message et cause.
     */
    public ColonneInexistanteException(String message, Throwable cause) {
        super(message, cause);
    }
}