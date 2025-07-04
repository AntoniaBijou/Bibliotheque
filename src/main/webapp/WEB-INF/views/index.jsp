<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Page de Connexion</title>
    <!-- <link href="${pageContext.request.contextPath}/assets/css/index.css" rel="stylesheet"> -->
</head>
<body>
    <div class="login-container">
        <h2 class="login-title">Connexion</h2>
        <form class="login-card" action="${pageContext.request.contextPath}/index" method="post">
            <div class="form-group">
                <label for="nom">Nom :</label>
                <input type="text" id="nom" name="nom" class="form-control" required />
            </div>

            <div class="form-group">
                <label for="email">Email :</label>
                <input type="email" id="email" name="email" class="form-control" required />
            </div>

            <div class="form-group">
                <label for="password">Mot de passe :</label>
                <input type="password" id="password" name="password" class="form-control" required />
            </div>

            <button type="submit" class="btn">Se connecter</button>
        </form>

        <c:if test="${not empty erreur}">
            <p class="error-message">${erreur}</p>
        </c:if>
    </div>
</body>
</html>