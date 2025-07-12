<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <html>

    <head>
        <title>Liste des Prolongements</title>
    </head>

    <body>
        <h2>Liste des demandes de prolongement</h2>

        <table border="1">
            <tr>
                <th>ID</th>
                <th>Adhérant</th>
                <th>Livre</th>
                <th>Date Demande</th>
                <th>Nouvelle date retour</th>
                <th>Motif</th>
                <th>Statut</th>
                <th>Action</th>
            </tr>
            <c:forEach var="pro" items="${prolongements}">
                <tr>
                    <td>${pro.idProlongement}</td>
                    <td>${pro.pret.adherant.nom}</td>
                    <td>${pro.pret.exemplaire.livre.titre}</td>
                    <td>${pro.dateDemande}</td>
                    <td>${pro.nouvelleDateRetour}</td>
                    <td>${pro.motif}</td>
                    <td>${pro.statut}</td>
                    <td>
                        <c:choose>
                            <c:when test="${pro.statut == 'en attente'}">
                                <form
                                    action="${pageContext.request.contextPath}/admin/prolongement/valider/${pro.idProlongement}"
                                    method="post" style="display:inline;">
                                    <button type="submit">Valider</button>
                                </form>
                                <form
                                    action="${pageContext.request.contextPath}/admin/prolongement/refuser/${pro.idProlongement}"
                                    method="post" style="display:inline;">
                                    <button type="submit">Refuser</button>
                                </form>
                            </c:when>
                            <c:when test="${pro.statut == 'en cours'}">
                                <span style="color: green; font-weight: bold;">Validé</span>
                            </c:when>
                            <c:when test="${pro.statut == 'refusé'}">
                                <span style="color: red; font-weight: bold;">Refusé</span>
                            </c:when>
                            <c:otherwise>
                                <span>Status inconnu</span>
                            </c:otherwise>
                        </c:choose>
                    </td>

                </tr>
            </c:forEach>
        </table>
    </body>

    </html>