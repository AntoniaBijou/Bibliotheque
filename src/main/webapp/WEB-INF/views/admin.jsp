<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login Admin</title>
</head>
<body>
    <h2>Connexion Admin</h2>

    <form action="${pageContext.request.contextPath}/admin" method="post">
        <label>Nom :</label>
        <input type="text" name="nom" required><br><br>

        <label>Email :</label>
        <input type="email" name="email" required><br><br>

        <label>Mot de passe :</label>
        <input type="password" name="password" required><br><br>

        <button type="submit">Se connecter</button>
    </form>

    <c:if test="${not empty erreur}">
        <p style="color:red">${erreur}</p>
    </c:if>
</body>
</html>
