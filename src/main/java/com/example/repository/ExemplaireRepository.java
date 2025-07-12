package com.example.repository;

import com.example.model.Exemplaire;
import com.example.model.Livre;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@Repository
public interface ExemplaireRepository extends JpaRepository<Exemplaire, Integer> {

    @Query("SELECT e FROM Exemplaire e WHERE e.livre.titre = :titre")
    Optional<Exemplaire> findByLivreTitre(@Param("titre") String titre);

    List<Exemplaire> findByLivre(Livre livre);
}
