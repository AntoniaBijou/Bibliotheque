package com.example.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Categorie")
public class Categorie {
    @Id
    @Column(name = "idCategorie", nullable = false)
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategorie;

    @Column(name = "nom", nullable = false, length = 50)
    private String nom;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private Set<Livre> livres = new HashSet<>();

    public Set<Livre> getLivres() {
        return livres;
    }

    public void setLivres(Set<Livre> livres) {
        this.livres = livres;
    }

    public Categorie() {
    }

    public Categorie(Integer idCategorie, String nom) {
        this.idCategorie = idCategorie;
        this.nom = nom;
    }

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