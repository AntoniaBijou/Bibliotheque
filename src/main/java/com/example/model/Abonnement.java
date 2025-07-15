package com.example.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Abonnement")
public class Abonnement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAbonnement")
    private Integer idAbonnement;

    @Column(name = "dateDebut", nullable = false)
    private Date dateDebut;

    @Column(name = "dateFin", nullable = false)
    private Date dateFin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAdherant", nullable = false)
    private Adherant adherant;

    public Integer getIdAbonnement() {
        return idAbonnement;
    }

    public void setIdAbonnement(Integer idAbonnement) {
        this.idAbonnement = idAbonnement;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Adherant getAdherant() {
        return adherant;
    }

    public void setAdherant(Adherant adherant) {
        this.adherant = adherant;
    }
}