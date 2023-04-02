package com.bioinformatics.spring.security.postgresql.payload.response;


public class ConflictDataException extends ModelException {

    public ConflictDataException(String message, Throwable cause) {
        super(message, cause);
    }

}
