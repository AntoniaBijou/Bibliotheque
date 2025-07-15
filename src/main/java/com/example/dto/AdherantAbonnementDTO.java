package com.example.dto;

import java.util.Date;

public class AdherantAbonnementDTO {

    private String nomAdherant;
    private String typeAdherant;
    private int nombreQuota;
    private Date dateDebut;
    private Date dateFin;

    public AdherantAbonnementDTO(String nomAdherant, String typeAdherant, int nombreQuota, Date dateDebut, Date dateFin) {
        this.nomAdherant = nomAdherant;
        this.typeAdherant = typeAdherant;
        this.nombreQuota = nombreQuota;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    public String getNomAdherant() {
        return nomAdherant;
    }

    public void setNomAdherant(String nomAdherant) {
        this.nomAdherant = nomAdherant;
    }

    public String getTypeAdherant() {
        return typeAdherant;
    }

    public void setTypeAdherant(String typeAdherant) {
        this.typeAdherant = typeAdherant;
    }

    public int getNombreQuota() {
        return nombreQuota;
    }

    public void setNombreQuota(int nombreQuota) {
        this.nombreQuota = nombreQuota;
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
}