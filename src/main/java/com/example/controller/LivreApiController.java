package com.example.controller;

import com.example.model.Livre;
import com.example.service.LivreService;
import com.example.dto.LivreDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/livre")
public class LivreApiController {
    private static final Logger LOG = LoggerFactory.getLogger(LivreApiController.class);

    @Autowired
    private LivreService livreService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getLivreDetails(@PathVariable("id") Integer id) {
        LOG.info("Requête pour les détails du livre avec idExemplaire : {}", id);
        try {
            Livre livre = livreService.getLivreByExemplaireId(id);
            if (livre == null) {
                LOG.warn("Livre non trouvé avec idExemplaire : {}", id);
                return ResponseEntity.status(404).body("Livre introuvable avec l'idExemplaire " + id);
            }

            LivreDTO livreDTO = new LivreDTO(
                livre.getIdLivre(),
                livre.getTitre(),
                livre.getIsbn(),
                livre.getEditeur(),
                livre.getAuteur() != null ? livre.getAuteur().getNom() : "Auteur inconnu",
                livreService.getTotalExemplaires(livre),
                livre.getCategories().stream()
                    .map(categorie -> categorie.getNom())
                    .collect(Collectors.toList())
            );
            LOG.info("Détails du livre récupérés avec succès pour idExemplaire : {}", id);
            return ResponseEntity.ok(livreDTO);
        } catch (Exception e) {
            LOG.error("Erreur lors de la récupération des détails du livre avec idExemplaire : {}", id, e);
            return ResponseEntity.status(500).body("Erreur interne du serveur : " + e.getMessage());
        }
    }
}