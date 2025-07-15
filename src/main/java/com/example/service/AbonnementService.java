package com.example.service;

import com.example.model.Abonnement;
import com.example.repository.AbonnementRepository;
import com.example.dto.AdherantAbonnementDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AbonnementService {

    @Autowired
    private AbonnementRepository abonnementRepository;

    @Transactional(readOnly = true)
    public List<AdherantAbonnementDTO> getAdherantAbonnementDetails(Integer idAdherant) {
        List<Abonnement> abonnements = abonnementRepository.findByAdherantIdWithDetails(idAdherant);
        return abonnements.stream()
                .map(abonnement -> new AdherantAbonnementDTO(
                        abonnement.getAdherant().getNom(),
                        abonnement.getAdherant().getTypeAdherant() != null ? abonnement.getAdherant().getTypeAdherant().getType() : "Inconnu",
                        abonnement.getAdherant().getTypeAdherant() != null ? abonnement.getAdherant().getTypeAdherant().getNombreQuota() : 0,
                        abonnement.getDateDebut(),
                        abonnement.getDateFin()
                ))
                .collect(Collectors.toList());
    }
}