package com.example.demo.DTO;

import lombok.Data;

import java.util.List;

@Data
public class UserEvaluationRequest {
    private Long evaluateurId;
    private Long evalueId;
    private List<Long> questionIds;
    private List<Integer> notes;
    private String remarques;
}
