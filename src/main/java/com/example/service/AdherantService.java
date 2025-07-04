package com.example.service;

import com.example.model.Adherant;
import com.example.repository.AdherantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdherantService {

    @Autowired
    private AdherantRepository adherantRepository;


    public Adherant verifierConnexion(String nom, String email, String motDePasse) {
        return adherantRepository.findByNomAndEmailAndMotDePasse(nom, email, motDePasse);
    }

    public Adherant getAdherantById(Integer id) {
        return adherantRepository.findById(id).orElse(null);
    }

    public Adherant enregistrerAdherant(Adherant adherant) {
        return adherantRepository.save(adherant);
    }

    public void supprimerAdherant(Integer id) {
        adherantRepository.deleteById(id);
    }

    

}
