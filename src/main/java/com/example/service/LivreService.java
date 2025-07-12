package com.example.service;

import com.example.model.Livre;
import com.example.projection.LivreVueProjection;
import com.example.repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.transaction.Transactional;

@Service
public class LivreService {

    @Autowired
    private LivreRepository livreRepository;

    @Transactional
    public List<LivreVueProjection> getLivresDepuisVue() {
        return livreRepository.getLivresDepuisVue();
    }

    public Livre getLivreByTitre(String titre) {
        System.out.println(titre);
        return livreRepository.findByTitre(titre);
    }

}
