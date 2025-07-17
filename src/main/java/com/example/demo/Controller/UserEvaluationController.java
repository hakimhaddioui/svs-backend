package com.example.demo.Controller;

import com.example.demo.DTO.UserEvaluationRequest;
import com.example.demo.Model.UserEvaluation;
import com.example.demo.Service.UserEvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/userevaluations")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class UserEvaluationController {

    private final UserEvaluationService userEvaluationService;

    // 🔍 GET: récupérer toutes les évaluations
    @GetMapping
    public ResponseEntity<List<UserEvaluation>> getAllUserEvaluations() {
        return ResponseEntity.ok(userEvaluationService.getAllUserEvaluations());
    }

    // ➕ POST: ajouter une nouvelle évaluation
    @PostMapping
    public ResponseEntity<UserEvaluation> createUserEvaluation(@RequestBody UserEvaluationRequest request) {
        UserEvaluation created = userEvaluationService.createUserEvaluation(
                request.getEvaluateurId(),
                request.getEvalueId(),
                request.getQuestionIds(),
                request.getNotes(),
                request.getRemarques()
        );
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserEvaluation> updateUserEvaluation(
            @PathVariable Long id,
            @RequestBody UserEvaluationRequest request
    ) {
        UserEvaluation updated = userEvaluationService.updateUserEvaluation(
                id,
                request.getEvaluateurId(),
                request.getEvalueId(),
                request.getQuestionIds(),
                request.getNotes(),
                request.getRemarques()
        );
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserEvaluation(@PathVariable Long id) {
        userEvaluationService.deleteUserEvaluation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countUserEvaluations() {
        return ResponseEntity.ok(userEvaluationService.countUserEvaluations());
    }

}

