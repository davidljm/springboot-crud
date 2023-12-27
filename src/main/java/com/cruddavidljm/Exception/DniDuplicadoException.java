package com.cruddavidljm.Exception;

public class DniDuplicadoException extends RuntimeException {

    public DniDuplicadoException() {
        super();
    }

    public DniDuplicadoException(String message) {
        super(message);
    }

    public DniDuplicadoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DniDuplicadoException(Throwable cause) {
        super(cause);
    }
}
