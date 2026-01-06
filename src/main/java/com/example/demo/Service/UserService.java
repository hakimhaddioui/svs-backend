package com.example.demo.Service;

import com.example.demo.Mailing.SendMail;
import com.example.demo.Model.Gare;
import com.example.demo.Model.Role;
import com.example.demo.Model.Utilisateurs;
import com.example.demo.Repository.GaresRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Utils.PasswordGenerator;
import com.example.demo.exception.Gare.GareNotFoundException;
import com.example.demo.exception.User.InvalidRoleException;
import com.example.demo.exception.User.MatriculeAlreadyExistsException;
import com.example.demo.exception.User.UserAlreadyExistsException;
import com.example.demo.exception.User.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordService passwordService;

    // Ajouter un utilisateur
    public Utilisateurs addUser(Utilisateurs utilisateurs) {
        // Vérifier si l'email existe déjà
        if (userRepository.existsByEmail(utilisateurs.getEmail())) {
            throw new UserAlreadyExistsException(utilisateurs.getEmail());
        }

        // Vérifier si le matricule existe déjà
        if (userRepository.existsByMatricule(utilisateurs.getMatricule())) {
            throw new MatriculeAlreadyExistsException(utilisateurs.getMatricule());
        }

        // Vérifier si le rôle est valide
        try {
            Role.valueOf(utilisateurs.getRole().toString());  // Vérifie si le rôle est dans l'énumération
        } catch (IllegalArgumentException e) {
            throw new InvalidRoleException(utilisateurs.getRole().toString());
        }

        // Définir un mot de passe par défaut
        String generatedPassword = "ONCF2025";  // Mot de passe par défaut

        // Hacher le mot de passe
        String hashedPassword = passwordService.hashPassword(generatedPassword);

        // Stocker le mot de passe haché dans 'motDePasse'
        utilisateurs.setMotDePasse(hashedPassword);

        // Sauvegarder l'utilisateur dans la base de données
        return userRepository.save(utilisateurs);
    }

    // Mettre à jour un utilisateur
    public Utilisateurs updateUser(Long id, Utilisateurs utilisateursDetails) {
        Optional<Utilisateurs> userOpt = userRepository.findById(id);
        if (!userOpt.isPresent()) {
            throw new UserNotFoundException("Utilisateur avec ID " + id + " non trouvé.");
        }

        Utilisateurs utilisateurs = userOpt.get();
        utilisateurs.setNom(utilisateursDetails.getNom());
        utilisateurs.setPrenom(utilisateursDetails.getPrenom());
        utilisateurs.setEmail(utilisateursDetails.getEmail());

        // Vérifier si le matricule est unique
        if (!utilisateurs.getMatricule().equals(utilisateursDetails.getMatricule()) && userRepository.existsByMatricule(utilisateursDetails.getMatricule())) {
            throw new MatriculeAlreadyExistsException(utilisateursDetails.getMatricule());
        }

        // Vérifier si le rôle est valide
        try {
            Role.valueOf(utilisateursDetails.getRole().toString());
        } catch (IllegalArgumentException e) {
            throw new InvalidRoleException(utilisateursDetails.getRole().toString());
        }

        utilisateurs.setRole(utilisateursDetails.getRole());
        utilisateurs.setLieuTravail(utilisateursDetails.getLieuTravail());

        // Si un mot de passe est fourni, il faut le hacher avant de le sauvegarder
        if (utilisateursDetails.getMotDePasse() != null && !utilisateursDetails.getMotDePasse().isEmpty()) {
            String hashedPassword = passwordService.hashPassword(utilisateursDetails.getMotDePasse());
            utilisateurs.setMotDePasse(hashedPassword);  // Mettre à jour le mot de passe haché
        }

        return userRepository.save(utilisateurs);
    }

    // Supprimer un utilisateur
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("Utilisateur avec ID " + id + " non trouvé.");
        }
        userRepository.deleteById(id);
    }

    // Récupérer tous les utilisateurs
    public List<Utilisateurs> getAllUsers() {
        return userRepository.findAll();
    }

    // Récupérer un utilisateur par ID
    public Optional<Utilisateurs> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Réinitialiser le mot de passe à sa valeur par défaut "ONCF2025"
    public Utilisateurs resetPasswordToOrigin(Long id) {
        Optional<Utilisateurs> userOpt = userRepository.findById(id);
        if (!userOpt.isPresent()) {
            throw new UserNotFoundException("Utilisateur avec ID " + id + " non trouvé.");
        }

        Utilisateurs utilisateurs = userOpt.get();

        // Hacher le mot de passe par défaut
        String hashedPassword = passwordService.hashPassword("ONCF2025");

        // Mettre à jour le mot de passe haché
        utilisateurs.setMotDePasse(hashedPassword);

        return userRepository.save(utilisateurs);
    }

    public long countUsers() {
        return userRepository.count();
    }

    public Utilisateurs authenticate(String matricule, String motDePasse) {
        // Vérifier si l'utilisateur existe dans la base de données
        Utilisateurs user = userRepository.findByMatricule(matricule);

        // Si l'utilisateur existe et que le mot de passe est correct
        if (user != null && new BCryptPasswordEncoder().matches(motDePasse, user.getMotDePasse())) {
            return user;  // Utilisateur authentifié
        }

        return null;  // Echec d'authentification
    }

    public Utilisateurs register(Utilisateurs user) {
        // Vérifier si le matricule est déjà pris
        if (userRepository.findByMatricule(user.getMatricule()) != null) {
            return null;  // Le matricule est déjà pris
        }

        // Encoder le mot de passe
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getMotDePasse());
        user.setMotDePasse(encodedPassword);

        // Sauvegarder l'utilisateur dans la base de données
        return userRepository.save(user);
    }
}
