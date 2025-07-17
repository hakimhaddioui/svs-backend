package com.example.demo.exception.User;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String email) {
        super("Un utilisateur avec l'email '" + email + "' existe déjà.");
    }
}
