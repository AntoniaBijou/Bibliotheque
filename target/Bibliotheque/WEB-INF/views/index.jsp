<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Page de Connexion</title>
    <!-- <link href="${pageContext.request.contextPath}/assets/css/index.css" rel="stylesheet"> -->
</head>
<body>
    <h2>Connexion</h2>
    <form action="${pageContext.request.contextPath}/index" method="post">
        <label>Nom :</label>
        <input type="text" name="nom" required /><br/>

        <label>Email :</label>
        <input type="email" name="email" required /><br/>

        <label>Mot de passe :</label>
        <input type="password" name="password" required /><br/>

        <input type="submit" value="Se connecter" />
    </form>

    <c:if test="${not empty erreur}">
        <p style="color:red">${erreur}</p>
    </c:if>
</body>
</html>
