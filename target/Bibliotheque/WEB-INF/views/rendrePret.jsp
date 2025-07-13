<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Rendre un livre</title>
</head>
<body>
    <h2>Rendu du livre : ${pret.exemplaire.livre.titre}</h2>

    <form action="${pageContext.request.contextPath}/pret/rendre/${pret.idPret}" method="post">
        <label>Date de retour réelle :</label>
        <input type="date" name="dateRetourEffective" required />
        <br><br>
        <button type="submit">Valider le retour</button>
    </form>

    <c:if test="${message != null}">
        <p style="color: red;">${message}</p>
    </c:if>
</body>
</html>
