package com.example.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Categorie")
public class Categorie {
    @Id
    @Column(name = "idCategorie", nullable = false)
    private Integer idCategorie;

    @Column(name = "nom", nullable = false, length = 50)
    private String nom;

    // Ajoutez cette collection dans la classe Categorie
    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private Set<Livre> livres = new HashSet<>();

    // Ajoutez le getter et setter
    public Set<Livre> getLivres() {
        return livres;
    }

    public void setLivres(Set<Livre> livres) {
        this.livres = livres;
    }

    // Constructeurs
    public Categorie() {
    }

    public Categorie(Integer idCategorie, String nom) {
        this.idCategorie = idCategorie;
        this.nom = nom;
    }

    // Getters et Setters
    public Integer getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Integer idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Categorie [idCategorie=" + idCategorie + ", nom=" + nom + "]";
    }
}