package com.example.demo.Model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // L'utilisateur évalué
    @ManyToOne
    @JoinColumn(name = "evalue_id")
    private Utilisateurs evalue;

    // L'évaluateur
    @ManyToOne
    @JoinColumn(name = "evaluateur_id")
    private Utilisateurs evaluateur;

    // Date de l’évaluation
    private LocalDate dateEvaluation;

    // Remarque générale sur l’évaluation
    private String remarques;

    // Note totale (calculée comme somme des notes par question)
    private int noteTotale;

    // Liste des réponses (chaque question avec sa note)
    @OneToMany(mappedBy = "userEvaluation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EvaluationReponse> reponses = new ArrayList<>();

    public void calculerNoteTotale() {
        this.noteTotale = reponses.stream()
                .mapToInt(EvaluationReponse::getNote)
                .sum();
    }

}
