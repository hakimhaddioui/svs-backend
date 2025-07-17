package com.example.demo.exception.Gare;

public class GareNotFoundException extends RuntimeException {

    public GareNotFoundException(String nom) {
        super("La gare avec le nom '" + nom + "' n'a pas été trouvée.");
    }
}
