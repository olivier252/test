package com.apside.prono.errors;

public class PlayerUnknownException extends PronoException {

    public PlayerUnknownException(long idPlayer) {
        super("Le joueur avec l'ID " + idPlayer + " n'existe pas !");
    }
}
