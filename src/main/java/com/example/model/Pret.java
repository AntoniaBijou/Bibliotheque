package com.example.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Pret")
public class Pret {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPret", nullable = false)
    private Integer idPret;

    @Column(name = "dateEmprunt", columnDefinition = "DATE")
    private LocalDate dateEmprunt;

    @Column(name = "dateRetour", columnDefinition = "DATE")
    private LocalDate dateRetour;

    @Column(name = "typePret", length = 50)
    private String typePret;

    @Column(name = "status", length = 50)
    private String status;

    @ManyToOne
    @JoinColumn(name = "idExemplaire", nullable = false)
    private Exemplaire exemplaire;

    @ManyToOne
    @JoinColumn(name = "idAdherant", nullable = false)
    private Adherant adherant;

    public Pret() {
    }

    public Pret(LocalDate dateEmprunt, LocalDate dateRetour,
            String typePret, String status, Exemplaire exemplaire,
            Adherant adherant) {
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
        this.typePret = typePret;
        this.status = status;
        this.exemplaire = exemplaire;
        this.adherant = adherant;
    }

    public Integer getIdPret() {
        return idPret;
    }

    public void setIdPret(Integer idPret) {
        this.idPret = idPret;
    }

    public LocalDate getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(LocalDate dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

    public LocalDate getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(LocalDate dateRetour) {
        this.dateRetour = dateRetour;
    }

    public String getTypePret() {
        return typePret;
    }

    public void setTypePret(String typePret) {
        this.typePret = typePret;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }

    public Adherant getAdherant() {
        return adherant;
    }

    public void setAdherant(Adherant adherant) {
        this.adherant = adherant;
    }
}