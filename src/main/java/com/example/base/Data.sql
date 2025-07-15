INSERT INTO TypeAdherant (type, nombreQuota) VALUES
('Anonyme', 2),
('Etudiants', 5),
('Professeurs', 1),
('Professionels', 10);

INSERT INTO Admin (idAdmin, nom, email, motDePasse) VALUES
('Jean', 'jean@gmail.com', '1234');

INSERT INTO Adherant (nom, email, motDePasse, idTypeAdherant) VALUES
('Rakoto', 'rakoto@gmail.com', '123', 2);

INSERT INTO Auteur(nom) 
VALUES 
('Victor Hugo'),
('Jules Verne'),
('Emile Zola'),
('Albert Camus'),
('Molière');

INSERT INTO Livre(titre, ISBN, age, editeur, idAuteur) 
VALUES 
('Les Miserables', 'ISBN-001-2025', 15, 'Editions Classiques', 1),
('Vingt Mille Lieues sous les Mers', 'ISBN-002-2025', 12, 'Aventure Editions', 2),
('Germinal', 'ISBN-003-2025', 16, 'Zola Editions', 3),
('L etranger', 'ISBN-004-2025', 17, 'Camus Editions', 4),
('Le Cid', 'ISBN-005-2025', 14, 'Theâtre Français', 5),
('Notre-Dame de Paris', 'ISBN-006-2025', 15, 'Victor Editions', 1),
('Voyage au Centre de la Terre', 'ISBN-007-2025', 12, 'Aventure Editions', 2),
('Therèse Raquin', 'ISBN-008-2025', 16, 'Zola Editions', 3),
('La Peste', 'ISBN-009-2025', 17, 'Camus Editions', 4),
('L Avare', 'ISBN-010-2025', 14, 'Theâtre Français', 5);

INSERT INTO Categorie(nom) 
VALUES 
('Roman'),
('Aventure'),
('Classique'),
('Science-Fiction'),
('Drame'),
('Histoire'),
('Theatre'),
('Philosophie'),
('Fantastique'),
('Nouvelle');

INSERT INTO LivreCategorie(idLivre, idCategorie) 
VALUES 
(1, 1), 
(2, 2), 
(3, 6), 
(4, 8), 
(5, 7), 
(6, 1), 
(7, 4), 
(8, 1),
(9, 8), 
(10, 7); 

INSERT INTO Exemplaire(nombre, idLivre) 
VALUES 
(5, 1),
(3, 2),
(4, 3),
(2, 4),
(6, 5),
(5, 6),
(3, 7),
(2, 8),
(4, 9),
(3, 10);

INSERT INTO Abonnement (dateDebut, dateFin, idAdherant) VALUES
('2025-01-01', '2025-05-01', 1);