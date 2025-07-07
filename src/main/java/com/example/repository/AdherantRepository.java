package com.example.repository;

import com.example.model.Adherant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdherantRepository extends JpaRepository<Adherant, Integer> {
    
    Adherant findByNomAndEmailAndMotDePasse(String nom, String email, String motDePasse);

    @Query("SELECT a FROM Adherant a WHERE a.nom = :nom")
    Adherant findByNom(@Param("nom") String nom);
}

