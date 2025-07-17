package com.example.demo.exception.Gare;

public class GareAlreadyExistsException extends RuntimeException {

    public GareAlreadyExistsException(String gareNom) {
        super("La gare avec le nom " + gareNom + " existe déjà.");
    }


}