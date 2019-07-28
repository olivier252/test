package com.apside.prono.errors;

public class PronoException extends RuntimeException {

    public PronoException() {
    }

    public PronoException(String message, Throwable cause) {
        super(message, cause);
    }

    public PronoException(String message) {
        super(message);
    }

    public PronoException(Throwable cause) {
        super(cause);
    }
}
