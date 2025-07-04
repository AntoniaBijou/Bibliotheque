
package com.example.model;

import javax.persistence.*;
@Entity
@Table(name = "Auteur")
public class Auteur {
    @Id
    @Column(name = "idAuteur", nullable = false)
    private Integer idAuteur;

    @Column(name = "nom", nullable = false, length = 50)
    private String nom;

    public Auteur() {
    }

    public Auteur(Integer idAuteur, String nom) {
        this.idAuteur = idAuteur;
        this.nom = nom;
    }

    public Integer getIdAuteur() {
        return idAuteur;
    }

    public void setIdAuteur(Integer idAuteur) {
        this.idAuteur = idAuteur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Auteur [idAuteur=" + idAuteur + ", nom=" + nom + "]";
    }
}
