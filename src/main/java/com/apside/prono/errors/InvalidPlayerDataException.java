package com.apside.prono.errors;


public class InvalidPlayerDataException extends PronoException {

    public InvalidPlayerDataException() {
        super("Le joueur n'existe pas");
    }
}
