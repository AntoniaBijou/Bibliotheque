<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Demande de Prolongement</title>
</head>
<body>
    <h2>Demande de prolongement</h2>

<form action="${pageContext.request.contextPath}/prolongement/enregistrer" method="post">
    <input type="hidden" name="idPret" value="${prolongement.pret.idPret}" />

    <label>Motif :</label><br>
    <textarea name="motif" rows="4" cols="50" required></textarea><br><br>

    <label>Nouvelle date de retour :</label><br>
    <input type="date" name="nouvelleDateRetour" required/><br><br>

    <button type="submit">Soumettre</button>
</form>

</body>
</html>
