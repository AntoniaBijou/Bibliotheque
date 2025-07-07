package com.example.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name = "Livre")
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 🟢 Ajoute cette ligne !
    @Column(name = "idLivre", nullable = false)
    private Integer idLivre;

    @Column(name = "titre", nullable = false, length = 50)
    private String titre;

    @Column(name = "ISBN", length = 50)
    private String isbn;

    @Column(name = "age")
    private Integer age;

    @Column(name = "editeur", length = 50)
    private String editeur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAuteur", nullable = false)
    private Auteur auteur;

    @OneToMany(mappedBy = "livre", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Exemplaire> exemplaires = new HashSet<>();

    // Ajoutez cette collection dans la classe Livre
    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "LivreCategorie", joinColumns = @JoinColumn(name = "idLivre"), inverseJoinColumns = @JoinColumn(name = "idCategorie"), uniqueConstraints = @UniqueConstraint(columnNames = {
            "idLivre", "idCategorie" }))
    private Set<Categorie> categories = new HashSet<>();

    // Ajoutez le getter et setter
    public Set<Categorie> getCategories() {
        return categories;
    }

    public void setCategories(Set<Categorie> categories) {
        this.categories = categories;
    }

    // Constructeurs
    public Livre() {
    }

    public Livre(Integer idLivre, String titre, String isbn, Integer age, String editeur, Auteur auteur) {
        this.idLivre = idLivre;
        this.titre = titre;
        this.isbn = isbn;
        this.age = age;
        this.editeur = editeur;
        this.auteur = auteur;
    }

    // Getters et Setters
    public Integer getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(Integer idLivre) {
        this.idLivre = idLivre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }

    // Ajoutez ces méthodes utilitaires
    public void addCategorie(Categorie categorie) {
        this.categories.add(categorie);
        categorie.getLivres().add(this);
    }

    public void removeCategorie(Categorie categorie) {
        this.categories.remove(categorie);
        categorie.getLivres().remove(this);
    }

    public Set<Exemplaire> getExemplaires() {
        return exemplaires;
    }

    public void setExemplaires(Set<Exemplaire> exemplaires) {
        this.exemplaires = exemplaires;
    }

    @Override
    public String toString() {
        return "Livre [idLivre=" + idLivre + ", titre=" + titre + ", isbn=" + isbn +
                ", age=" + age + ", editeur=" + editeur + "]";
    }

}