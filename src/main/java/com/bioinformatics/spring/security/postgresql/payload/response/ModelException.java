package com.bioinformatics.spring.security.postgresql.payload.response;

public class ModelException extends RuntimeException {

    public ModelException(String message) {
        super(message);
    }

    public ModelException(String message, Throwable cause) {
        super(message, cause);
    }

}
