package com.example.service;

import com.example.model.Adherant;
import com.example.model.Abonnement;
import com.example.model.Exemplaire;
import com.example.model.Pret;
import com.example.model.TypeAdherant;
import com.example.repository.AdherantRepository;
import com.example.repository.AbonnementRepository;
import com.example.repository.ExemplaireRepository;
import com.example.repository.PretRepository;
import com.example.repository.TypeAdherantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class PretService {

    private static final Logger LOG = LoggerFactory.getLogger(PretService.class);

    @Autowired
    @Lazy
    private AdherantRepository adherantRepository;

    @Autowired
    @Lazy
    private ExemplaireRepository exemplaireRepository;

    @Autowired
    @Lazy
    private PretRepository pretRepository;

    @Autowired
    @Lazy
    private AbonnementRepository abonnementRepository;

    @Autowired
    @Lazy
    private TypeAdherantRepository typeAdherantRepository;

    @Transactional
    public boolean ajouterPret(Integer idAdherant, Integer idExemplaire, String typePret,
            LocalDate dateEmprunt, LocalDate dateRetour, String status) {

        // 1. Recherche des entités associées
        Optional<Adherant> adherantOpt = adherantRepository.findById(idAdherant);
        Optional<Exemplaire> exemplaireOpt = exemplaireRepository.findById(idExemplaire);

        if (!adherantOpt.isPresent() || !exemplaireOpt.isPresent()) {
            LOG.warn("Adhérant ou exemplaire non trouvé : idAdherant={}, idExemplaire={}", idAdherant, idExemplaire);
            return false;
        }

        Adherant adherant = adherantOpt.get();
        Exemplaire exemplaire = exemplaireOpt.get();

        // 2. Vérification de l'abonnement
        Optional<Abonnement> abonnementOpt = abonnementRepository.findByAdherant(adherant);
        if (!abonnementOpt.isPresent()) {
            LOG.warn("Aucun abonnement trouvé pour l'adhérent : idAdherant={}", idAdherant);
            return false;
        }

        Abonnement abonnement = abonnementOpt.get();
        LocalDate debutAbonnement = abonnement.getDateDebut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate finAbonnement = abonnement.getDateFin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        if (dateEmprunt.isBefore(debutAbonnement) || dateEmprunt.isAfter(finAbonnement) ||
                dateRetour.isBefore(debutAbonnement) || dateRetour.isAfter(finAbonnement)) {
            LOG.warn("Les dates du prêt ({}-{}) ne sont pas dans la période d'abonnement ({}-{})",
                    dateEmprunt, dateRetour, debutAbonnement, finAbonnement);
            return false;
        }

        TypeAdherant typeAdherant = adherant.getTypeAdherant();
        if (typeAdherant == null || typeAdherant.getNombreQuota() <= 0) {
            LOG.warn("Quota insuffisant pour l'adhérent : idAdherant={}, quota={}",
                    idAdherant, typeAdherant != null ? typeAdherant.getNombreQuota() : "null");
            return false;
        }

        Pret pret = new Pret();
        pret.setAdherant(adherant);
        pret.setExemplaire(exemplaire);
        pret.setDateEmprunt(dateEmprunt);
        pret.setDateRetour(dateRetour);
        pret.setTypePret(typePret);
        pret.setStatus(status);

        pretRepository.save(pret);

        typeAdherant.setNombreQuota(typeAdherant.getNombreQuota() - 1);
        typeAdherantRepository.save(typeAdherant);

        LOG.info("Prêt créé avec ID: {} pour adhérant ID: {} et exemplaire ID: {}, quota restant: {}",
                pret.getIdPret(), idAdherant, idExemplaire, typeAdherant.getNombreQuota());

        return true;
    }

    public Pret trouverParId(Integer idPret) {
        Optional<Pret> pret = pretRepository.findById(idPret);
        return pret.orElse(null);
    }

    @Transactional
    public Pret enregistrerPret(Pret pret) {
        return pretRepository.save(pret);
    }

    public AbonnementRepository getAbonnementRepository() {
        return abonnementRepository;
    }
}