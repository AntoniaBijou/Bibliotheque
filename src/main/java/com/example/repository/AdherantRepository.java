package com.example.repository;

import com.example.model.Adherant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdherantRepository extends JpaRepository<Adherant, Integer> {
    
    Adherant findByNomAndEmailAndMotDePasse(String nom, String email, String motDePasse);

    Adherant findByNom(String nom);

    

}
