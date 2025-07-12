<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

        <html>

        <head>
            <title>Page d'accueil</title>
            <link href="${pageContext.request.contextPath}/assets/css/acceuil.css" rel="stylesheet">

        </head>

        <body>
            <div class="sidebar">
                <h2>Menu</h2>
                <a href="acceuil">Accueil</a>
                <a href="reservations">Liste des livres reserves</a>
                <a href="admin">Admin</a>

                <form class="logout-form" action="logout" method="post">
                    <button type="submit">Se deconnecter</button>
                </form>
            </div>

            <div class="content">
                <h2>Liste des livres</h2>
                <table border="1">
                    <tr>
                        <th>Titre du Livre</th>
                        <th>Nom de l'Auteur</th>
                        <th>Categories</th>
                        <th>Exemplaires</th>
                        <th>Action</th>
                    </tr>

                    <c:forEach var="livre" items="${livres}">
                        <tr>
                            <td>${livre.titreLivre}</td>
                            <td>${livre.nomAuteur}</td>
                            <td>${livre.categories}</td>
                            <td>${livre.nombreExemplaires}</td>

                            <td>
                                <form action="formReservation" method="get">
                                    <input type="hidden" name="idExemplaire" value="${livre.idExemplaire}" />
                                    <button type="submit">Reserver</button>
                                </form>

                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </body>

        </html>