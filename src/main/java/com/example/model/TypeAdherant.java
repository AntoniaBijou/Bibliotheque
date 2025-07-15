package com.example.model;

import javax.persistence.*;

@Entity
@Table(name = "TypeAdherant")
public class TypeAdherant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTypeAdherant")
    private Integer idTypeAdherant;

    @Column(name = "type")
    private String type;

    @Column(name = "nombreQuota")
    private Integer nombreQuota;

    public Integer getIdTypeAdherant() {
        return idTypeAdherant;
    }

    public void setIdTypeAdherant(Integer idTypeAdherant) {
        this.idTypeAdherant = idTypeAdherant;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getNombreQuota() {
        return nombreQuota;
    }

    public void setNombreQuota(Integer nombreQuota) {
        this.nombreQuota = nombreQuota;
    }
}