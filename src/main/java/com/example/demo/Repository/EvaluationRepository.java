package com.example.demo.Repository;

import com.example.demo.Model.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {

    // Méthode pour vérifier si une évaluation existe par son nom
    boolean existsByNom(String nom);

    // Méthode pour trouver une évaluation par son nom
    Optional<Evaluation> findByNom(String nom);

}
