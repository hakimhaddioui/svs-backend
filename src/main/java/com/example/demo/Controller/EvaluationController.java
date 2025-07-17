package com.example.demo.Controller;

import com.example.demo.Model.Evaluation;
import com.example.demo.Service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/evaluations")
public class EvaluationController {

    private final EvaluationService evaluationService;

    @Autowired
    public EvaluationController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    // 1. Créer une nouvelle évaluation
    @PostMapping
    public ResponseEntity<Evaluation> createEvaluation(@RequestBody Evaluation evaluation) {
        evaluation.setId(null); // Assurez-vous que l'ID est null pour une création
        Evaluation savedEvaluation = evaluationService.saveEvaluation(evaluation);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvaluation);
    }

    // 2. Récupérer une évaluation par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Evaluation> getEvaluationById(@PathVariable Long id) {
        Optional<Evaluation> evaluationOptional = evaluationService.findById(id);
        return evaluationOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 3. Mettre à jour une évaluation existante
    @PutMapping("/{id}")
    public ResponseEntity<Evaluation> updateEvaluation(@PathVariable Long id, @RequestBody Evaluation evaluationDetails) {
        Optional<Evaluation> optionalEvaluation = evaluationService.findById(id);

        if (optionalEvaluation.isPresent()) {
            Evaluation existingEvaluation = optionalEvaluation.get();
            existingEvaluation.setNom(evaluationDetails.getNom());
            existingEvaluation.setDescription(evaluationDetails.getDescription());
            existingEvaluation.setNoteMax(evaluationDetails.getNoteMax());

            Evaluation updatedEvaluation = evaluationService.saveEvaluation(existingEvaluation);
            return ResponseEntity.ok(updatedEvaluation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 4. Supprimer une évaluation par son ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvaluation(@PathVariable Long id) {
        Optional<Evaluation> evaluationOptional = evaluationService.findById(id);
        if (evaluationOptional.isPresent()) {
            evaluationService.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content pour une suppression réussie
        }
        return ResponseEntity.notFound().build(); // 404 Not Found si l'ID n'existe pas
    }

    // 5. Récupérer toutes les évaluations
    @GetMapping
    public Iterable<Evaluation> getAllEvaluations() {
        return evaluationService.findAll();
    }

    // 6. Récupérer une évaluation par son nom
    @GetMapping("/nom/{nom}")
    public ResponseEntity<Evaluation> getEvaluationByNom(@PathVariable String nom) {
        Optional<Evaluation> evaluation = evaluationService.findByNom(nom);
        return evaluation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countEvaluations() {
        long count = evaluationService.countEvaluations();
        return ResponseEntity.ok(count);
    }

}
