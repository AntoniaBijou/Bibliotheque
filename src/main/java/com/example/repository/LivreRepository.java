package com.example.repository;

import com.example.model.Livre;
import com.example.projection.LivreVueProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LivreRepository extends JpaRepository<Livre, Integer> {

    @Query(value = "SELECT idExemplaire AS idExemplaire, Titre_Livre AS titreLivre, Nom_Auteur AS nomAuteur, Categories AS categories, Nombre_Exemplaires AS nombreExemplaires FROM Vue_Livres_Auteurs_Categories", nativeQuery = true)
    List<LivreVueProjection> getLivresDepuisVue();

    @Query("SELECT l FROM Livre l LEFT JOIN FETCH l.auteur LEFT JOIN FETCH l.categories LEFT JOIN FETCH l.exemplaires WHERE l.idLivre = :id")
    Optional<Livre> findByIdWithAuteurAndCategoriesAndExemplaires(@Param("id") Integer id);

    @Query("SELECT l FROM Livre l WHERE LOWER(l.titre) = LOWER(:titre)")
    Livre findByTitre(@Param("titre") String titre);
}