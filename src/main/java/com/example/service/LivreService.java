package com.example.service;

import com.example.model.Livre;
import com.example.model.Exemplaire;
import com.example.projection.LivreVueProjection;
import com.example.repository.LivreRepository;
import com.example.repository.ExemplaireRepository;
import com.example.dto.LivreDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivreService {

    @Autowired
    private LivreRepository livreRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Transactional
    public List<LivreVueProjection> getLivresDepuisVue() {
        return livreRepository.getLivresDepuisVue();
    }

    public Livre getLivreByTitre(String titre) {
        return livreRepository.findByTitre(titre);
    }

    public Livre getLivreById(Integer id) {
        return livreRepository.findById(id).orElse(null);
    }

    @Transactional
    public Livre getLivreByExemplaireId(Integer idExemplaire) {
        return exemplaireRepository.findById(idExemplaire)
                .map(Exemplaire::getLivre)
                .map(livre -> livreRepository.findByIdWithAuteurAndCategoriesAndExemplaires(livre.getIdLivre()).orElse(null))
                .orElse(null);
    }

    public int getTotalExemplaires(Livre livre) {
        return livre.getExemplaires()
                    .stream()
                    .mapToInt(ex -> ex.getNombre() != null ? ex.getNombre() : 0)
                    .sum();
    }

    public List<LivreDTO> getLivresDTO() {
        List<Livre> livres = livreRepository.findAll();
        return livres.stream()
                .map(l -> new LivreDTO(
                        l.getIdLivre(),
                        l.getTitre(),
                        l.getIsbn(),
                        l.getEditeur(),
                        l.getAuteur() != null ? l.getAuteur().getNom() : "Auteur inconnu",
                        getTotalExemplaires(l),
                        l.getCategories().stream()
                            .map(categorie -> categorie.getNom())
                            .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }
}