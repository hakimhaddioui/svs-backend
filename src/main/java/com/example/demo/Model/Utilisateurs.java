package com.example.demo.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "utilisateurs") // mieux de préciser pour éviter conflit dans la base
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateurs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String matricule;  // Matricule de l'utilisateur

    private String nom;
    private String prenom;
    private String profil;  // Profil (ex : Administrateur, Manager, etc.)

    private String email;
    private String motDePasse;

    @Enumerated(EnumType.STRING)
    private Role role;  // Le rôle de l'utilisateur (QAF, MP, ADMIN....)

    private String lieuTravail;  // Lieu de travail : "siège : central/regional" ou "nom_gare"

    @OneToMany(mappedBy = "evalue")
    @JsonIgnore
    private List<UserEvaluation> evaluationsRecues;

    @OneToMany(mappedBy = "evaluateur")
    @JsonIgnore
    private List<UserEvaluation> evaluationsEffectuees;


}
