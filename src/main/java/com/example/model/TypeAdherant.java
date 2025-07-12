package com.example.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TypeAdherant")
public class TypeAdherant {
    @Id
    @Column(name = "idTypeAdherant", nullable = false)
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTypeAdherant;

    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @Column(name = "nombreQuota", nullable = false)
    private Integer nombreQuota;

    @OneToMany(mappedBy = "typeAdherant", fetch = FetchType.LAZY)
    private Set<Adherant> adherants = new HashSet<>();

    public TypeAdherant() {
    }

    public TypeAdherant(Integer idTypeAdherant, String type, Integer nombreQuota) {
        this.idTypeAdherant = idTypeAdherant;
        this.type = type;
        this.nombreQuota = nombreQuota;
    }

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

    public Set<Adherant> getAdherants() {
        return adherants;
    }

    public void addAdherant(Adherant adherant) {
        this.adherants.add(adherant);
        adherant.setTypeAdherant(this);
    }

    public void removeAdherant(Adherant adherant) {
        this.adherants.remove(adherant);
        adherant.setTypeAdherant(null);
    }

    @Override
    public String toString() {
        return "TypeAdherant{" +
               "idTypeAdherant=" + idTypeAdherant +
               ", type='" + type + '\'' +
               ", nombreQuota=" + nombreQuota +
               '}';
    }
}