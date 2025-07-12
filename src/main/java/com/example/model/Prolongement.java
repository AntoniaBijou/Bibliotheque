package com.example.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Prolongement")
public class Prolongement {
    @Id
    @Column(name = "idProlongement", nullable = false)
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProlongement;

    @Column(name = "dateDemande", nullable = false, columnDefinition = "DATE")
    private LocalDate dateDemande;

    @Column(name = "NouvelleDateRetour", nullable = false, columnDefinition = "DATE")
    private LocalDate nouvelleDateRetour;

    @Column(name = "statut", nullable = false, length = 50)
    private String statut;

    @Column(name = "motif", length = 100)
    private String motif;

    @ManyToOne
    @JoinColumn(name = "idPret", nullable = false)
    private Pret pret;

    public Prolongement() {
    }

    public Prolongement(Integer idProlongement, LocalDate dateDemande, 
                      LocalDate nouvelleDateRetour, String statut, 
                      String motif, Pret pret) {
        this.idProlongement = idProlongement;
        this.dateDemande = dateDemande;
        this.nouvelleDateRetour = nouvelleDateRetour;
        this.statut = statut;
        this.motif = motif;
        this.pret = pret;
    }

    public Integer getIdProlongement() {
        return idProlongement;
    }

    public void setIdProlongement(Integer idProlongement) {
        this.idProlongement = idProlongement;
    }

    public LocalDate getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(LocalDate dateDemande) {
        this.dateDemande = dateDemande;
    }

    public LocalDate getNouvelleDateRetour() {
        return nouvelleDateRetour;
    }

    public void setNouvelleDateRetour(LocalDate nouvelleDateRetour) {
        this.nouvelleDateRetour = nouvelleDateRetour;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public Pret getPret() {
        return pret;
    }

    public void setPret(Pret pret) {
        this.pret = pret;
    }
    

    @Override
    public String toString() {
        return "Prolongement{" +
               "idProlongement=" + idProlongement +
               ", dateDemande=" + dateDemande +
               ", nouvelleDateRetour=" + nouvelleDateRetour +
               ", statut='" + statut + '\'' +
               ", motif='" + motif + '\'' +
               '}';
    }
}