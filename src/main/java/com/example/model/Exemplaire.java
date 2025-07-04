package com.example.model;

import javax.persistence.*;

@Entity
@Table(name = "Exemplaire")
public class Exemplaire {
    @Id
    @Column(name = "idExemplaire", nullable = false)
    private Integer idExemplaire;

    @Column(name = "nombre")
    private Integer nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idLivre", nullable = false)
    private Livre livre;

    // Constructeurs
    public Exemplaire() {
    }

    public Exemplaire(Integer idExemplaire, Integer nombre, Livre livre) {
        this.idExemplaire = idExemplaire;
        this.nombre = nombre;
        this.livre = livre;
    }

    // Getters et Setters
    public Integer getIdExemplaire() {
        return idExemplaire;
    }

    public void setIdExemplaire(Integer idExemplaire) {
        this.idExemplaire = idExemplaire;
    }

    public Integer getNombre() {
        return nombre;
    }

    public void setNombre(Integer nombre) {
        this.nombre = nombre;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    @Override
    public String toString() {
        return "Exemplaire [idExemplaire=" + idExemplaire + ", nombre=" + nombre + "]";
    }
}