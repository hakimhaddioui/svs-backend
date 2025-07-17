package com.example.demo.Repository;

import com.example.demo.Model.Gare;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GaresRepository extends JpaRepository<Gare, Long> {

    boolean existsByNom(String nom);
    Optional<Gare> findByNom(String nom);

}
