<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

        <!DOCTYPE html>
        <html>

        <head>
            <title>Admin Dashboard</title>
            <link href="${pageContext.request.contextPath}/assets/css/adminDashboard.css" rel="stylesheet">

        </head>

        <body>

            <div class="sidebar">
                <h3>Admin</h3>
                <ul>
                    <li><a href="${pageContext.request.contextPath}/admin/dashboard">Tableau de bord</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/listePrets">Liste des prêts</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/formPret">Emprunt</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/listeProlongements">Liste des prolongements</a></li>

                    <li><a href="${pageContext.request.contextPath}/">Accueil</a></li>
                </ul>
                <form method="post" action="${pageContext.request.contextPath}/admin/logout">
                    <button class="logout-btn">Se deconnecter</button>
                </form>
            </div>



            <div class="main">
                <h2>Liste de toutes les reservations</h2>
                <p>Total reservations : ${fn:length(reservations)}</p>
                <table border="1">
                    <tr>
                        <th>ID</th>
                        <th>Adherant</th>
                        <th>Livre</th>
                        <th>Date</th>
                        <th>Statut</th>
                        <th>Actions</th>
                    </tr>
                    <c:forEach var="r" items="${reservations}">
                        <tr>
                            <td>${r.idReservation}</td>
                            <td>${r.nomAdherant}</td>
                            <td>${r.titreLivre}</td>
                            <td>${r.dateReservation}</td>
                            <td>${r.statut}</td>
                            <td>
                                <form action="${pageContext.request.contextPath}/admin/valider" method="post">
                                    <input type="hidden" name="idReservation" value="${r.idReservation}" />
                                    <button type="submit" class="btn-valider">Valider</button>
                                </form>

                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

        </body>

        </html>