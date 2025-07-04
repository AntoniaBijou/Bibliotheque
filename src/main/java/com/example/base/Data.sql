INSERT INTO TypeAdherant (idTypeAdherant, type, nombreQuota) VALUES
(1, 'Anonyme', 2),
(2, 'Etudiants', 5),
(3, 'Professeurs', 1),
(4, 'Professeurs', 10);

INSERT INTO Adherant (idAdherant, nom, email, motDePasse, idTypeAdherant) VALUES
(1, 'Rakoto', 'rakoto@gmail.com', '123', 2);

INSERT INTO Auteur (idAuteur, nom) VALUES
(1, 'Jean Rakotomalala'),
(2, 'Soa Randrianarisoa'),
(3, 'Mamy Andrianina');

INSERT INTO Livre (idLivre, titre, ISBN, age, editeur, idAuteur) VALUES
(1, 'Ny Andro Ratsy', '978-1234567890', 12, 'Trano Printy Malagasy', 1),
(2, 'Fahendrena Malagasy', '978-0987654321', 16, 'Editions Soa', 2),
(3, 'Tantara Gasy', '978-1122334455', 10, 'Maison du Livre', 3);

INSERT INTO Categorie (idCategorie, nom) VALUES
(1, 'Roman'),
(2, 'Culture Malagasy'),
(3, 'Histoire');

INSERT INTO LivreCategorie (idLivre, idCategorie) VALUES
(1, 1), -- Ny Andro Ratsy → Roman
(2, 2), -- Fahendrena Malagasy → Culture Malagasy
(3, 3), -- Tantara Gasy → Histoire
(2, 1); -- Fahendrena Malagasy → aussi Roman

INSERT INTO Exemplaire (idExemplaire, nombre, idLivre) VALUES
(1, 3, 1), -- 3 exemplaires du livre id=1 (Ny Andro Ratsy)
(2, 5, 2), -- 5 exemplaires du livre id=2 (Fahendrena Malagasy)
(3, 2, 3); -- 2 exemplaires du livre id=3 (Tantara Gasy)

INSERT INTO Admin (idAdmin, nom, email, motDePasse) VALUES
(1, 'Jean', 'jean@gmail.com', '1234');
