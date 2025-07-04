CREATE OR REPLACE VIEW Vue_Livres_Auteurs_Categories AS
SELECT 
    e.idExemplaire AS idExemplaire,
    l.titre AS Titre_Livre,
    a.nom AS Nom_Auteur,
    GROUP_CONCAT(DISTINCT c.nom SEPARATOR ', ') AS Categories,
    COALESCE(SUM(e.nombre), 0) AS Nombre_Exemplaires
FROM Livre l
JOIN Auteur a ON l.idAuteur = a.idAuteur
JOIN LivreCategorie lc ON l.idLivre = lc.idLivre
JOIN Categorie c ON lc.idCategorie = c.idCategorie
LEFT JOIN Exemplaire e ON l.idLivre = e.idLivre
GROUP BY e.idExemplaire;


CREATE VIEW Vue_Reservations_Detaillees AS
SELECT 
    r.idReservation,
    r.dateReservation,
    r.statut,
    a.nom AS nomAdherant,
    l.titre AS titreLivre
FROM Reservation r
JOIN Adherant a ON r.idAdherant = a.idAdherant
JOIN Exemplaire e ON r.idExemplaire = e.idExemplaire
JOIN Livre l ON e.idLivre = l.idLivre;
