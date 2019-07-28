package com.apside.prono.errors;

public class InvalidEventDataException extends PronoException {

    public InvalidEventDataException() {
        super("Données de l'événement invalides");
    }
}
