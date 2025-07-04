package com.example.model;

import javax.persistence.Entity;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReservation;

    private LocalDate dateReservation;
    private String statut;

    private int idAdherant;
    private int idExemplaire;
    public int getIdReservation() {
        return idReservation;
    }
    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }
    public LocalDate getDateReservation() {
        return dateReservation;
    }
    public void setDateReservation(LocalDate dateReservation) {
        this.dateReservation = dateReservation;
    }
    public String getStatut() {
        return statut;
    }
    public void setStatut(String statut) {
        this.statut = statut;
    }
    public int getIdAdherant() {
        return idAdherant;
    }
    public void setIdAdherant(int idAdherant) {
        this.idAdherant = idAdherant;
    }
    public int getIdExemplaire() {
        return idExemplaire;
    }
    public void setIdExemplaire(int idExemplaire) {
        this.idExemplaire = idExemplaire;
    }
    public Reservation(int idReservation, LocalDate dateReservation, String statut, int idAdherant, int idExemplaire) {
        this.idReservation = idReservation;
        this.dateReservation = dateReservation;
        this.statut = statut;
        this.idAdherant = idAdherant;
        this.idExemplaire = idExemplaire;
    }
    public Reservation() {
    }

    
    // getters & setters
}
