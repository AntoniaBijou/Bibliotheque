package com.example.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
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
    private AdherantRepository adherantRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Autowired
    private PretRepository pretRepository;

    public boolean ajouterPret(String nomAdherant, String titreLivre, String typePret,
                           LocalDate dateEmprunt, LocalDate dateRetour, String status) {
    Adherant adherant = adherantRepository.findByNom(nomAdherant);
    Exemplaire exemplaire = exemplaireRepository.findByLivreTitre(titreLivre); // méthode correcte

    if (adherant != null && exemplaire != null) {
        Pret pret = new Pret();
        pret.setAdherant(adherant);        // attention : objet, pas ID
        pret.setExemplaire(exemplaire);    // idem
        pret.setTypePret(typePret);
        
        pret.setDateEmprunt(dateEmprunt);
        pret.setDateRetour(dateRetour);
        pret.setStatus(status);

        pretRepository.save(pret); // ⛳ insertion ici
        return true;
    }
    return false;
}

}
