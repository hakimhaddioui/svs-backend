package com.example.demo.exception.User;

public class MatriculeAlreadyExistsException extends RuntimeException {
    public MatriculeAlreadyExistsException(String matricule) {
        super("Le matricule " + matricule + " existe déjà.");
    }
}
