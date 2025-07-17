package com.example.demo.Controller;


import com.example.demo.Model.Gare;
import com.example.demo.Repository.GaresRepository;
import com.example.demo.Service.GareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/gares")
public class GareController {

    @Autowired
    private GareService gareService;

    @Autowired
    private GaresRepository garesRepository;

    // Récupérer toutes les gares
    @GetMapping
    public List<Gare> getAllGares() {
        return gareService.getAllGares();
    }

    // Récupérer une gare par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Gare> getGareById(@PathVariable Long id) {
        Optional<Gare> gare = gareService.getGareById(id);
        if (gare.isPresent()) {
            return ResponseEntity.ok(gare.get());
        }
        return ResponseEntity.notFound().build();
    }

    // Ajouter une nouvelle gare
    @PostMapping
    public ResponseEntity<Gare> addGare(@RequestBody Gare gare) {
        Gare newGare = gareService.addGare(gare);
        return ResponseEntity.ok(newGare);
    }

    // Mettre à jour une gare
    // Mettre à jour une gare par son ID
    @PutMapping("/{id}")
    public ResponseEntity<Gare> updateGare(@PathVariable Long id, @RequestBody Gare gareDetails) {
        Gare updatedGare = gareService.updateGare(id, gareDetails);
        return ResponseEntity.ok(updatedGare);
    }


    // Supprimer une gare
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGare(@PathVariable Long id) {
        gareService.deleteGare(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/gares/count")
    public long getGareCount() {
        return garesRepository.count();  // Retourner le nombre d'utilisateurs
    }
}
