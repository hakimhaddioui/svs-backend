package com.example.demo.Service;

import com.example.demo.Model.Evaluation;
import com.example.demo.Repository.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EvaluationService {

    private final EvaluationRepository evaluationRepository;

    @Autowired
    public EvaluationService(EvaluationRepository evaluationRepository) {
        this.evaluationRepository = evaluationRepository;
    }

    // Créer ou mettre à jour une évaluation
    public Evaluation saveEvaluation(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }

    // Trouver une évaluation par son nom
    public Optional<Evaluation> findByNom(String nom) {
        return evaluationRepository.findByNom(nom);
    }

    // Vérifier si une évaluation existe par son nom
    public boolean existsByNom(String nom) {
        return evaluationRepository.existsByNom(nom);
    }

    // Récupérer toutes les évaluations
    public Iterable<Evaluation> findAll() {
        return evaluationRepository.findAll();
    }

    // Récupérer une évaluation par son id
    public Optional<Evaluation> findById(Long id) {
        return evaluationRepository.findById(id);
    }

    // Supprimer une évaluation par son id
    public void deleteById(Long id) {
        evaluationRepository.deleteById(id);
    }

    // Supprimer une évaluation
    public void delete(Evaluation evaluation) {
        evaluationRepository.delete(evaluation);
    }

    public long countEvaluations() {
        return evaluationRepository.count();
    }

}
