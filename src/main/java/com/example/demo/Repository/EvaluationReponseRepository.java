package com.example.demo.Repository;

import com.example.demo.Model.EvaluationReponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvaluationReponseRepository extends JpaRepository<EvaluationReponse, Long> {

    List<EvaluationReponse> findByUserEvaluation_Id(Long userEvaluationId);

    List<EvaluationReponse> findByQuestion_Id(Long questionId);
}