package com.example.dto;

import java.util.List;

public class LivreDTO {
    private Integer idLivre;
    private String titre;
    private String isbn;
    private String editeur;
    private String nomAuteur;
    private int nombreExemplaires;
    private List<String> categories;

    public LivreDTO() {
    }

    public LivreDTO(Integer idLivre, String titre, String isbn, String editeur, 
                    String nomAuteur, int nombreExemplaires, List<String> categories) {
        this.idLivre = idLivre;
        this.titre = titre;
        this.isbn = isbn;
        this.editeur = editeur;
        this.nomAuteur = nomAuteur;
        this.nombreExemplaires = nombreExemplaires;
        this.categories = categories;
    }

    // Getters and setters
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

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public String getNomAuteur() {
        return nomAuteur;
    }

    public void setNomAuteur(String nomAuteur) {
        this.nomAuteur = nomAuteur;
    }

    public int getNombreExemplaires() {
        return nombreExemplaires;
    }

    public void setNombreExemplaires(int nombreExemplaires) {
        this.nombreExemplaires = nombreExemplaires;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}