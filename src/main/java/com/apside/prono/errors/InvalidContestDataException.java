package com.apside.prono.errors;

public class InvalidContestDataException extends PronoException {
    public InvalidContestDataException() {
        super("Données du concours invalides");
    }
}
