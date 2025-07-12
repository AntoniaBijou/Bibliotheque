package com.example.service;

import com.example.model.Prolongement;
import com.example.repository.ProlongementRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProlongementService {

    @Autowired
    private ProlongementRepository prolongementRepository;

    public Prolongement enregistrerProlongement(Prolongement prolongement) {
        System.out.println(">>> Sauvegarde du prolongement en base");
        return prolongementRepository.save(prolongement);
    }

    public Prolongement trouverParId(Integer id) {
        return prolongementRepository.findById(id).orElse(null);
    }

    public List<Prolongement> listeTous() {
        return prolongementRepository.findAll();
    }
}
