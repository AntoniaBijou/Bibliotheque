package com.example.repository;

import com.example.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

    @Query("SELECT a FROM Admin a WHERE a.nom = ?1 AND a.email = ?2 AND a.motDePasse = ?3")
    Admin verifierConnexion(String nom, String email, String motDePasse);
}
