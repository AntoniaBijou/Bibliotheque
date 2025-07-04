<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Formulaire de prêt</title>
</head>
<body>
    <h2>Ajouter un nouveau prêt</h2>
    <form action="${pageContext.request.contextPath}/admin/savePret" method="post">
        <label>Nom de l'adhérant :</label>
        <input type="text" name="nomAdherant" required /><br/>

        <label>Titre du livre :</label>
        <input type="text" name="titreLivre" required /><br/>

        <label>Type de prêt :</label>
        <select name="typePret">
            <option value="sur place">Sur place</option>
            <option value="à domicile">À domicile</option>
        </select><br/>

        <label>Date d'emprunt :</label>
        <input type="date" name="dateEmprunt" required /><br/>

        <label>Date de retour :</label>
        <input type="date" name="dateRetour" required /><br/>

        <label>Statut :</label>
        <select name="status">
            <option value="en cours">En cours</option>
            <option value="retourné">Retourné</option>
        </select><br/>

        <button type="submit">Valider le prêt</button>
    </form>
</body>
</html>
