package com.example.demo.exception.User;

public class InvalidRoleException extends RuntimeException {
    public InvalidRoleException(String role) {
        super("Le rôle " + role + " n'existe pas.");
    }
}
