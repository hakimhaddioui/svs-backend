package com.example.demo.Repository;

import com.example.demo.Model.UserEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface UserEvaluationRepository extends JpaRepository<UserEvaluation, Long> {

    List<UserEvaluation> findByEvalue_Id(Long evalueId);

    List<UserEvaluation> findByEvaluateur_Id(Long evaluateurId);
}
