package com.example.demo.exception;

import com.example.demo.exception.Gare.GareAlreadyExistsException;
import com.example.demo.exception.Gare.GareNotFoundException;
import com.example.demo.exception.User.InvalidRoleException;
import com.example.demo.exception.User.MatriculeAlreadyExistsException;
import com.example.demo.exception.User.UserAlreadyExistsException;
import com.example.demo.exception.User.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Gérer l'exception "Gare non trouvée"
    @ExceptionHandler(GareNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleGareNotFound(GareNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Gérer l'exception "Gare déjà existante"
    @ExceptionHandler(GareAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleGareAlreadyExists(GareAlreadyExistsException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());  // Renvoie le message sous forme de JSON
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Gérer les autres exceptions générales
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {
        ex.printStackTrace();
        Map<String, String> response = new HashMap<>();
        response.put("message", "Une erreur interne est survenue.");
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Gérer l'exception "Utilisateur non trouvé"
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFound(UserNotFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Gérer l'exception "Utilisateur déjà existant"
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleUserAlreadyExists(UserAlreadyExistsException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MatriculeAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleMatriculeAlreadyExists(MatriculeAlreadyExistsException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Gérer l'exception "Rôle non valide"
    @ExceptionHandler(InvalidRoleException.class)
    public ResponseEntity<Map<String, String>> handleInvalidRoleException(InvalidRoleException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }





}
