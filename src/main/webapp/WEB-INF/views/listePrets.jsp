<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des prets - Admin</title>
    <link href="${pageContext.request.contextPath}/assets/css/adminDashboard.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>

<body>
    <div class="admin-container">
        <!-- Sidebar -->
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
                <li class="active">
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
                <li>
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

        <!-- Main Content -->
        <div class="admin-main">
            <div class="main-header">
                <h2><i class="fas fa-book"></i> Liste des prets</h2>
                <div class="stats-card">
                    <i class="fas fa-clipboard-list"></i>
                    <span>Total prets: ${fn:length(prets)}</span>
                </div>
            </div>

            <div class="table-container">
                <table class="admin-table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Adherent</th>
                            <th>Livre</th>
                            <th>Date Emprunt</th>
                            <th>Date Retour</th>
                            <th>Type</th>
                            <th>Statut</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="pret" items="${prets}">
                            <tr>
                                <td>${pret.idPret}</td>
                                <td>${pret.adherant.nom}</td>
                                <td>${pret.exemplaire.livre.titre}</td>
                                <td>${pret.dateEmprunt}</td>
                                <td>${pret.dateRetour}</td>
                                <td>${pret.typePret}</td>
                                <td>
                                    <span class="status-badge ${pret.status.toLowerCase().replace(' ', '-')}">
                                        ${pret.status}
                                    </span>
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