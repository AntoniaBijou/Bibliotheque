CREATE DATABASE bibliotheque;
USE bibliotheque;

CREATE TABLE TypeAdherant(
    idTypeAdherant INT AUTO_INCREMENT,
    type VARCHAR(50),
    nombreQuota INT,
    PRIMARY KEY(idTypeAdherant)
);

CREATE TABLE Adherant(
   idAdherant INT AUTO_INCREMENT,
   nom VARCHAR(50),
   email VARCHAR(50),
   motDePasse VARCHAR(50),
   idTypeAdherant INT,
   FOREIGN KEY(idTypeAdherant) REFERENCES TypeAdherant(idTypeAdherant),
   PRIMARY KEY(idAdherant)
);

CREATE TABLE Auteur(
   idAuteur INT AUTO_INCREMENT,
   nom VARCHAR(50),
   PRIMARY KEY(idAuteur)
);

CREATE TABLE Livre(
   idLivre INT AUTO_INCREMENT,
   titre VARCHAR(50),
   ISBN VARCHAR(50),
   age INT,
   editeur VARCHAR(50),
   idAuteur INT NOT NULL,
   PRIMARY KEY(idLivre),
   FOREIGN KEY(idAuteur) REFERENCES Auteur(idAuteur)
);

CREATE TABLE Categorie(
   idCategorie INT AUTO_INCREMENT,
   nom VARCHAR(50),
   PRIMARY KEY(idCategorie)
);

CREATE TABLE LivreCategorie(
   idLivre INT,
   idCategorie INT,
   PRIMARY KEY(idLivre, idCategorie),
   FOREIGN KEY(idLivre) REFERENCES Livre(idLivre),
   FOREIGN KEY(idCategorie) REFERENCES Categorie(idCategorie)
);

CREATE TABLE Exemplaire(
   idExemplaire INT AUTO_INCREMENT,
   nombre INT,
   idLivre INT NOT NULL,
   PRIMARY KEY(idExemplaire),
   FOREIGN KEY(idLivre) REFERENCES Livre(idLivre)
);

CREATE TABLE Pret(
   idPret INT AUTO_INCREMENT,
   dateEmprunt DATE,
   dateRetour DATE,
   typePret VARCHAR(50),
   status VARCHAR(50),
   idExemplaire INT NOT NULL,
   idAdherant INT NOT NULL,
   PRIMARY KEY(idPret),
   FOREIGN KEY(idExemplaire) REFERENCES Exemplaire(idExemplaire),
   FOREIGN KEY(idAdherant) REFERENCES Adherant(idAdherant)
);

CREATE TABLE Prolongement(
   idProlongement INT AUTO_INCREMENT,
   dateDemande DATE,
   NouvelleDateRetour DATE,
   statut VARCHAR(50),
   motif VARCHAR(100),
   idPret INT,
   PRIMARY KEY(idProlongement),
   FOREIGN KEY(idPret) REFERENCES Pret(idPret)
);

CREATE TABLE Reservation (
    idReservation INT AUTO_INCREMENT,
    dateReservation DATE NOT NULL,
    statut VARCHAR(50) DEFAULT 'en attente',
    idAdherant INT NOT NULL,
    idExemplaire INT NOT NULL,
    PRIMARY KEY(idReservation),
    FOREIGN KEY(idAdherant) REFERENCES Adherant(idAdherant),
    FOREIGN KEY(idExemplaire) REFERENCES Exemplaire(idExemplaire)
);

CREATE TABLE Admin(
   idAdmin INT AUTO_INCREMENT,
   nom VARCHAR(50),
   email VARCHAR(50),
   motDePasse VARCHAR(50),
   PRIMARY KEY(idAdmin)
);

CREATE TABLE Abonnement (
    idAbonnement INT AUTO_INCREMENT,
    dateDebut DATE NOT NULL,
    dateFin DATE NOT NULL,
    idAdherant INT NOT NULL,
    PRIMARY KEY(idAbonnement),
    FOREIGN KEY(idAdherant) REFERENCES Adherant(idAdherant)
);
