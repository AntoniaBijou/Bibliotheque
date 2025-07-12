package com.example.service;

import java.time.LocalDate;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.example.model.Adherant;
import com.example.model.Exemplaire;
import com.example.model.Pret;
import com.example.repository.AdherantRepository;
import com.example.repository.ExemplaireRepository;
import com.example.repository.PretRepository;

@Service
public class PretService {

    @Autowired
    @Lazy
    private AdherantRepository adherantRepository;

    @Autowired
    @Lazy
    private ExemplaireRepository exemplaireRepository;

    @Autowired
    @Lazy
    private PretRepository pretRepository;

    @Transactional
    public boolean ajouterPret(Integer idAdherant, Integer idExemplaire, String typePret,
            LocalDate dateEmprunt, LocalDate dateRetour, String status) {

        // 1. Recherche des entités associées
        Optional<Adherant> adherant = adherantRepository.findById(idAdherant);
        Optional<Exemplaire> exemplaire = exemplaireRepository.findById(idExemplaire);

        // 2. Vérification de l'existence
        if (!adherant.isPresent() || !exemplaire.isPresent()) {
            return false;
        }

        // 3. Création et configuration du prêt
        Pret pret = new Pret();

        // Setters obligatoires (champs NOT NULL)
        pret.setAdherant(adherant.get());
        pret.setExemplaire(exemplaire.get());
        pret.setDateEmprunt(dateEmprunt);
        pret.setDateRetour(dateRetour);

        // Setters pour les autres champs
        pret.setTypePret(typePret);
        pret.setStatus(status);

        // 4. Sauvegarde
        pretRepository.save(pret);

        // 5. Log pour débogage
        System.out.println("Prêt créé avec ID: " + pret.getIdPret()
                + " pour adhérent ID: " + idAdherant
                + " et exemplaire ID: " + idExemplaire);

        return true;
    }

}
