package com.example.repository;

import com.example.model.Livre;
import com.example.projection.LivreVueProjection;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LivreRepository extends CrudRepository<Livre, Integer> {

@Query(value = "SELECT idExemplaire AS idExemplaire, Titre_Livre AS titreLivre, Nom_Auteur AS nomAuteur, Categories AS categories, Nombre_Exemplaires AS nombreExemplaires FROM Vue_Livres_Auteurs_Categories", nativeQuery = true)
List<LivreVueProjection> getLivresDepuisVue();

    
}

