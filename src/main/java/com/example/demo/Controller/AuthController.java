package com.example.demo.Controller;

import com.example.demo.Model.Utilisateurs;
import com.example.demo.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Utilisateurs user) {
        Utilisateurs authenticatedUser = userService.authenticate(user.getMatricule(), user.getMotDePasse());

        if (authenticatedUser != null) {
            // Créer une réponse JSON au lieu de texte brut
            Map<String, String> response = new HashMap<>();
            response.put("message", "Connexion réussie, Bienvenue " + authenticatedUser.getMatricule());
            return ResponseEntity.ok(response);
        } else {
            // Retourner une erreur JSON avec un message d'erreur
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Matricule ou mot de passe incorrect");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Utilisateurs user) {
        Utilisateurs registeredUser = userService.register(user);
        if (registeredUser != null) {
            return ResponseEntity.ok("Inscription réussie pour " + registeredUser.getMatricule());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Matricule déjà utilisé");
        }
    }
}
