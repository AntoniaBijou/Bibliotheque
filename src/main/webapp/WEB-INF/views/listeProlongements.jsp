<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Prolongements - Admin</title>
    <link href="${pageContext.request.contextPath}/assets/css/adminDashboard.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
    <div class="admin-container">
        <div class="admin-sidebar">
            <div class="sidebar-header">
                <i class="fas fa-user-shield"></i>
                <h3>Espace Admin</h3>
            </div>
            
            <ul class="sidebar-menu">
                <li>
                    <a href="${pageContext.request.contextPath}/admin/dashboard">
                        <i class="fas fa-tachometer-alt"></i>
                        <span>Tableau de bord</span>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/admin/listePrets">
                        <i class="fas fa-book"></i>
                        <span>Liste des prets</span>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/admin/formPret">
                        <i class="fas fa-hand-holding"></i>
                        <span>Nouvel emprunt</span>
                    </a>
                </li>
                <li class="active">
                    <a href="${pageContext.request.contextPath}/admin/listeProlongements">
                        <i class="fas fa-calendar-plus"></i>
                        <span>Prolongements</span>
                    </a>
                </li>
            </ul>
            
            <form method="post" action="${pageContext.request.contextPath}/admin/logout" class="sidebar-footer">
                <button class="logout-btn">
                    <i class="fas fa-sign-out-alt"></i>
                    <span>Deconnexion</span>
                </button>
            </form>
        </div>

        <div class="admin-main">
            <div class="main-header">
                <h2><i class="fas fa-calendar-plus"></i> Demandes de prolongement</h2>
                <div class="stats-card">
                    <i class="fas fa-clipboard-list"></i>
                    <span>Total demandes: ${fn:length(prolongements)}</span>
                </div>
            </div>

            <div class="table-container">
                <table class="admin-table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Adherent</th>
                            <th>Livre</th>
                            <th>Date Demande</th>
                            <th>Nouvelle date</th>
                            <th>Motif</th>
                            <th>Statut</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="pro" items="${prolongements}">
                            <tr>
                                <td>${pro.idProlongement}</td>
                                <td>${pro.pret.adherant.nom}</td>
                                <td>${pro.pret.exemplaire.livre.titre}</td>
                                <td>${pro.dateDemande}</td>
                                <td>${pro.nouvelleDateRetour}</td>
                                <td>${pro.motif}</td>
                                <td>
                                    <span class="status-badge ${pro.statut.toLowerCase().replace('e', 'e')}">
                                        ${pro.statut}
                                    </span>
                                </td>
                                <td class="actions-cell">
                                    <c:choose>
                                        <c:when test="${pro.statut == 'en attente'}">
                                            <form action="${pageContext.request.contextPath}/admin/prolongement/valider/${pro.idProlongement}" method="post">
                                                <button type="submit" class="action-btn validate-btn">
                                                    <i class="fas fa-check"></i> Valider
                                                </button>
                                            </form>
                                            <form action="${pageContext.request.contextPath}/admin/prolongement/refuser/${pro.idProlongement}" method="post">
                                                <button type="submit" class="action-btn cancel-btn">
                                                    <i class="fas fa-times"></i> Refuser
                                                </button>
                                            </form>
                                        </c:when>
                                        <c:when test="${pro.statut == 'en cours'}">
                                            <span class="status-success"><i class="fas fa-check-circle"></i> Valide</span>
                                        </c:when>
                                        <c:when test="${pro.statut == 'refuse'}">
                                            <span class="status-error"><i class="fas fa-times-circle"></i> Refuse</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span>Status inconnu</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>