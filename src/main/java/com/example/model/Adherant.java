package com.example.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Adherant")
public class Adherant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAdherant", nullable = false)
    private Integer idAdherant;

    @Column(name = "nom", nullable = false, length = 50)
    private String nom;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "motDePasse", length = 50)
    private String motDePasse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idTypeAdherant")
    private TypeAdherant typeAdherant;

    @OneToMany(mappedBy = "adherant", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Pret> prets = new HashSet<>();

    public Adherant() {
    }

    public Adherant(Integer idAdherant, String nom, String email,
            String motDePasse, TypeAdherant typeAdherant) {
        this.idAdherant = idAdherant;
        this.nom = nom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.typeAdherant = typeAdherant;
    }

    public Integer getIdAdherant() {
        return idAdherant;
    }

    public void setIdAdherant(Integer idAdherant) {
        this.idAdherant = idAdherant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public TypeAdherant getTypeAdherant() {
        return typeAdherant;
    }

    public void setTypeAdherant(TypeAdherant typeAdherant) {
        this.typeAdherant = typeAdherant;
    }

    public Set<Pret> getPrets() {
        return prets;
    }

    public void addPret(Pret pret) {
        this.prets.add(pret);
        pret.setAdherant(this);
    }

    public void removePret(Pret pret) {
        this.prets.remove(pret);
        pret.setAdherant(null);
    }

    @Override
    public String toString() {
        return "Adherant{" +
                "idAdherant=" + idAdherant +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}