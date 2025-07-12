<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <!DOCTYPE html>
    <html>

    <head>
        <title>Liste des prêts</title>
    </head>

    <body>

        <h2>Liste de tous les prêts</h2>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Adherant</th>
                <th>Livre</th>
                <th>Date Emprunt</th>
                <th>Date Retour</th>
                <th>Type</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            <c:forEach var="pret" items="${prets}">
                <tr>
                    <td>${pret.idPret}</td>
                    <td>${pret.adherant.nom}</td>
                    <td>${pret.exemplaire.livre.titre}</td>
                    <td>${pret.dateEmprunt}</td>
                    <td>${pret.dateRetour}</td>
                    <td>${pret.typePret}</td>
                    <td>${pret.status}</td>
                    <td>
                        <button type="button">rendre</button>
                        <a href="${pageContext.request.contextPath}/prolongement/demande/${pret.idPret}">
                            <button type="button">Prolonger</button>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>

    </body>

    </html>