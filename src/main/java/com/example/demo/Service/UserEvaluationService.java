package com.example.demo.Service;

import com.example.demo.Model.*;
import com.example.demo.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserEvaluationService {

    private final UserEvaluationRepository userEvaluationRepository;
    private final UserRepository utilisateursRepository;
    private final EvaluationRepository evaluationRepository;

    // 🔍 1. Voir toutes les évaluations
    public List<UserEvaluation> getAllUserEvaluations() {
        return userEvaluationRepository.findAll();
    }

    // ➕ 2. Ajouter une nouvelle UserEvaluation
    public UserEvaluation createUserEvaluation(
            Long evaluateurId,
            Long evalueId,
            List<Long> questionIds,
            List<Integer> notes,
            String remarque
    ) {
        if (questionIds.size() != notes.size()) {
            throw new IllegalArgumentException("Chaque question doit avoir une note correspondante");
        }

        Utilisateurs evaluateur = utilisateursRepository.findById(evaluateurId)
                .orElseThrow(() -> new RuntimeException("Évaluateur non trouvé"));
        Utilisateurs evalue = utilisateursRepository.findById(evalueId)
                .orElseThrow(() -> new RuntimeException("Évalué non trouvé"));

        UserEvaluation userEvaluation = new UserEvaluation();
        userEvaluation.setEvaluateur(evaluateur);
        userEvaluation.setEvalue(evalue);
        userEvaluation.setDateEvaluation(LocalDate.now());
        userEvaluation.setRemarques(remarque);

        // Créer les réponses
        int noteTotale = 0;
        for (int i = 0; i < questionIds.size(); i++) {
            Evaluation question = evaluationRepository.findById(questionIds.get(i))
                    .orElseThrow(() -> new RuntimeException("Question non trouvée"));

            EvaluationReponse reponse = new EvaluationReponse();
            reponse.setQuestion(question);
            reponse.setNote(notes.get(i));
            reponse.setUserEvaluation(userEvaluation);

            userEvaluation.getReponses().add(reponse);
            noteTotale += notes.get(i);
        }

        userEvaluation.setNoteTotale(noteTotale);
        return userEvaluationRepository.save(userEvaluation);
    }

    public UserEvaluation updateUserEvaluation(
            Long id,
            Long evaluateurId,
            Long evalueId,
            List<Long> questionIds,
            List<Integer> notes,
            String remarque
    ) {
        UserEvaluation userEvaluation = userEvaluationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Évaluation non trouvée"));

        if (questionIds.size() != notes.size()) {
            throw new IllegalArgumentException("Chaque question doit avoir une note correspondante");
        }

        Utilisateurs evaluateur = utilisateursRepository.findById(evaluateurId)
                .orElseThrow(() -> new RuntimeException("Évaluateur non trouvé"));
        Utilisateurs evalue = utilisateursRepository.findById(evalueId)
                .orElseThrow(() -> new RuntimeException("Évalué non trouvé"));

        userEvaluation.setEvaluateur(evaluateur);
        userEvaluation.setEvalue(evalue);
        userEvaluation.setRemarques(remarque);
        userEvaluation.setDateEvaluation(LocalDate.now());

        userEvaluation.getReponses().clear();

        int noteTotale = 0;
        for (int i = 0; i < questionIds.size(); i++) {
            Evaluation question = evaluationRepository.findById(questionIds.get(i))
                    .orElseThrow(() -> new RuntimeException("Question non trouvée"));

            EvaluationReponse reponse = new EvaluationReponse();
            reponse.setQuestion(question);
            reponse.setNote(notes.get(i));
            reponse.setUserEvaluation(userEvaluation);

            userEvaluation.getReponses().add(reponse);
            noteTotale += notes.get(i);
        }

        userEvaluation.setNoteTotale(noteTotale);
        return userEvaluationRepository.save(userEvaluation);
    }

    public void deleteUserEvaluation(Long id) {
        UserEvaluation evaluation = userEvaluationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Évaluation introuvable"));
        userEvaluationRepository.delete(evaluation);
    }

    public long countUserEvaluations() {
        return userEvaluationRepository.count();
    }


}
