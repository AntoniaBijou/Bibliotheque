package com.example.repository;

import com.example.model.Abonnement;
import com.example.model.Adherant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AbonnementRepository extends JpaRepository<Abonnement, Integer> {

    @Query("SELECT a FROM Abonnement a JOIN FETCH a.adherant ad JOIN FETCH ad.typeAdherant WHERE a.adherant.idAdherant = :idAdherant")
    List<Abonnement> findByAdherantIdWithDetails(@Param("idAdherant") Integer idAdherant);

    Optional<Abonnement> findByAdherant(Adherant adherant);
}