package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.model.Exemplaire;

public interface ExemplaireRepository extends JpaRepository<Exemplaire, Integer> {
@Query("SELECT e FROM Exemplaire e WHERE e.livre.titre = :titre")
Exemplaire findByLivreTitre(@Param("titre") String titre);
}

