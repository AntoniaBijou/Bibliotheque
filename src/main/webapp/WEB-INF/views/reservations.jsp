<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Liste des Réservations</title>
    <!-- <link href="${pageContext.request.contextPath}/assets/css/reservation.css" rel="stylesheet"> -->
</head>
<body>
    <h2>Liste des Réservations</h2>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Adhérant</th>
            <th>Livre</th>
            <th>Date de réservation</th>
            <th>Statut</th>
        </tr>

        <c:forEach var="r" items="${reservations}">
            <tr>
                <td>${r.idReservation}</td>
                <td>${r.nomAdherant}</td>
                <td>${r.titreLivre}</td>
                <td>${r.dateReservation}</td>
                <td>${r.statut}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
