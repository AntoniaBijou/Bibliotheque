<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Reservation</title>
    <link href="${pageContext.request.contextPath}/assets/css/formReservation.css" rel="stylesheet">

</head>
<body>
    <h2>Formulaire de reservation</h2>

    <form action="reserver" method="post">
        <input type="hidden" name="idExemplaire" value="${idExemplaire}" />

        <label for="nomAdherant">Nom de l’Adherant :</label>
<input type="text" name="nomAdherant" required />


        <label for="dateReservation">Date de reservation :</label>
        <input type="date" name="dateReservation" value="<%= java.time.LocalDate.now() %>" required /><br/><br/>

        <label for="statut">Statut :</label>
        <select name="statut">
            <option value="en attente">En attente</option>
            <option value="confirmee">Confirmee</option>
            <option value="annulee">Annulee</option>
        </select><br/><br/>

        <button type="submit">Valider</button>
    </form>
</body>
</html>
