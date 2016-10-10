package fr.fmi.pickaname.core.exception;

public class TechnicalException extends Exception {

    public TechnicalException(final String message, final Throwable exception) {
        super(message, exception);
    }

}
