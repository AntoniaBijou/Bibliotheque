package com.example.controller;

import com.example.dto.AdherantAbonnementDTO;
import com.example.service.AbonnementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/adherant")
public class AdherantApiController {

    private static final Logger LOG = LoggerFactory.getLogger(AdherantApiController.class);

    @Autowired
    private AbonnementService abonnementService;

    @GetMapping("/{id}/abonnements")
    public ResponseEntity<?> getAdherantAbonnementDetails(@PathVariable("id") Integer idAdherant) {
        LOG.info("Requête pour les détails de l'abonnement de l'adhérent ID : {}", idAdherant);
        try {
            List<AdherantAbonnementDTO> abonnements = abonnementService.getAdherantAbonnementDetails(idAdherant);
            if (abonnements.isEmpty()) {
                LOG.warn("Aucun abonnement trouvé pour l'adhérent ID : {}", idAdherant);
                return ResponseEntity.status(404).body("Aucun abonnement trouvé pour l'adhérent ID : " + idAdherant);
            }
            LOG.info("Détails de l'abonnement récupérés avec succès pour l'adhérent ID : {}", idAdherant);
            return ResponseEntity.ok(abonnements);
        } catch (Exception e) {
            LOG.error("Erreur lors de la récupération des détails de l'abonnement pour l'adhérent ID : {}", idAdherant, e);
            return ResponseEntity.status(500).body("Erreur interne du serveur : " + e.getMessage());
        }
    }
}