package com.example.demo.Repository;

import com.example.demo.Model.Utilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Utilisateurs, Long> {
    boolean existsByEmail(String email);
    Optional<Utilisateurs> findByEmail(String email);
    boolean existsByMatricule(String matricule);
}
