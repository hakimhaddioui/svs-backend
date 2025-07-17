package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationReponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // À quelle évaluation utilisateur cette réponse appartient
    @ManyToOne
    @JoinColumn(name = "user_evaluation_id")
    @JsonIgnore
    private UserEvaluation userEvaluation;

    // La question posée
    @ManyToOne
    @JoinColumn(name = "evaluation_id")
    private Evaluation question;

    // Note donnée pour cette question
    private int note;
}

