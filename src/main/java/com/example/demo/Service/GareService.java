package com.example.demo.Service;

import com.example.demo.Model.Gare;
import com.example.demo.Repository.GaresRepository;
import com.example.demo.exception.Gare.GareAlreadyExistsException;
import com.example.demo.exception.Gare.GareNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GareService {

    @Autowired
    private GaresRepository gareRepository;

    public Gare addGare(Gare gare) {
        // Vérifier si une gare avec le même nom existe déjà
        if (gareRepository.existsByNom(gare.getNom())) {
            throw new GareAlreadyExistsException(gare.getNom());  // Lancer une exception si le nom existe déjà
        }
        return gareRepository.save(gare);
    }

    // Modifier une gare existante
    public Gare updateGare(Long id, Gare gareDetails) {
        // Vérifier si la gare existe avec l'ID
        Optional<Gare> gareOpt = gareRepository.findById(id);
        if (!gareOpt.isPresent()) {
            throw new GareNotFoundException("La gare avec l'ID " + id + " n'a pas été trouvée");
        }

        // Vérifier si une autre gare existe déjà avec le nouveau nom
        if (gareRepository.existsByNom(gareDetails.getNom()) && !gareOpt.get().getNom().equals(gareDetails.getNom())) {
            throw new GareAlreadyExistsException(gareDetails.getNom());
        }

        // Mettre à jour la gare
        Gare gare = gareOpt.get();
        gare.setNom(gareDetails.getNom());
        gare.setEmplacement(gareDetails.getEmplacement());
        return gareRepository.save(gare);
    }

    // Supprimer une gare
    public void deleteGare(Long id) {
        gareRepository.deleteById(id);
    }

    // Obtenir toutes les gares
    public List<Gare> getAllGares() {
        return gareRepository.findAll();
    }

    // Obtenir une gare par son ID
    public Optional<Gare> getGareById(Long id) {
        return gareRepository.findById(id);
    }
}
