INSERT INTO TypeAdherant (type, nombreQuota) VALUES
('Etudiant', 2),
('Enseignant', 3),
('Professionels', 4);

INSERT INTO Admin (nom, email, motDePasse) VALUES
('Jean', 'jean@gmail.com', '1234');

INSERT INTO Adherant (nom, email, motDePasse, idTypeAdherant) VALUES
('Amine', 'Amine@gmail.com', '111', 1),
('Sarah', 'Sarah@gmail.com', '222', 1),
('Youssef', 'Youssef@gmail.com', '333', 1),
('Nadia', 'Nadia@gmail.com', '444', 2),
('Karim', 'Karim@gmail.com', '555', 2),
('Salima', 'Salima@gmail.com', '666', 2),
('Rachid', 'Rachid@gmail.com', '777', 3),
('Amina', 'Amina@gmail.com', '888', 3);


INSERT INTO Auteur(nom) 
VALUES 
('Victor Hugo'),
('Albert Camus'),
('JK Rowling');

INSERT INTO Livre(titre, ISBN, age, editeur, idAuteur) 
VALUES 
('Les Miserables', '9782070409189', 20, 'Editions Classiques', 1),
('L etranger', '9782070360022', 18, 'Aventure Editions', 2),
('Harry Potter a l ecole des sorciers', '9782070643026', 25, 'Zola Editions', 3);

INSERT INTO Categorie(nom) 
VALUES 
('Litterature classique'),
('Philosophie'),
('Jeunesse'),
('Fantastique');

INSERT INTO LivreCategorie(idLivre, idCategorie) 
VALUES 
(1, 1), 
(2, 2), 
(3, 3);
(3, 4);

INSERT INTO Exemplaire(nombre, idLivre) 
VALUES 
(3, 1),
(2, 2),
(1, 3);

INSERT INTO Abonnement (dateDebut, dateFin, idAdherant) VALUES
('2025-02-01', '2025-07-01', 2),
('2025-04-01', '2025-12-01', 3),
('2025-07-01', '2026-07-01', 4),
('2025-08-01', '2026-05-01', 5),
('2025-07-01', '2026-06-01', 6),
('2025-06-01', '2025-12-01', 7),
('2025-10-01', '2025-06-01', 8);