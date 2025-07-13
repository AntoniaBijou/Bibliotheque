package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.example.model.Exemplaire;
import com.example.model.Livre;
import com.example.repository.ExemplaireRepository;

@Service
public class ExemplaireService {

    @Autowired
    @Lazy
    private ExemplaireRepository exemplaireRepository;

    @Autowired
    @Lazy
    private LivreService livreService;

    public Exemplaire getExemplaireByLivreTitre(String titre) {
        Livre livre = livreService.getLivreByTitre(titre);
        List<Exemplaire> exemplaires = exemplaireRepository.findByLivre(livre);
        if (exemplaires.isEmpty()) {
            return null;
        }
        return exemplaires.get(0);
    }

    public void enregistrer(Exemplaire exemplaire) {
        exemplaireRepository.save(exemplaire);
    }

}
