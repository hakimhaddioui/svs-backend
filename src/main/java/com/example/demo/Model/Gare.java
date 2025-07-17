package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data // Lombok crée automatiquement les getters, setters, toString, equals, hashCode
@NoArgsConstructor // Lombok crée un constructeur sans argument
@AllArgsConstructor // Lombok crée un constructeur avec tous les arguments
public class Gare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String emplacement;

}
