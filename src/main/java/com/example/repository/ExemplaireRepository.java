package com.example.repository;

import com.example.model.Exemplaire;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ExemplaireRepository extends JpaRepository<Exemplaire, Integer> {

    @Query("SELECT e FROM Exemplaire e WHERE e.livre.titre = :titre")
    Optional<Exemplaire> findByLivreTitre(@Param("titre") String titre);
}
