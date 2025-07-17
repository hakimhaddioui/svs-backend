package com.example.demo.Service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    // Hacher le mot de passe
    public String hashPassword(String password) {
        // Générer un salt (génère un nouveau salt chaque fois)
        String salt = BCrypt.gensalt();
        // Hacher le mot de passe avec le salt généré
        return BCrypt.hashpw(password, salt);
    }

    // Vérifier si un mot de passe correspond au hachage
    public boolean checkPassword(String password, String hashedPassword) {
        // Vérifier si le mot de passe correspond au hachage
        return BCrypt.checkpw(password, hashedPassword);
    }
}
