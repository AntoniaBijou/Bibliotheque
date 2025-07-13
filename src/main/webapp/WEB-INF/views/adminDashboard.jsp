<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

        <!DOCTYPE html>
        <html>

        <head>
            <title>Admin Dashboard</title>
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
                        <li class="active">
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
                        <h2><i class="fas fa-bookmark"></i> Liste des reservations</h2>
                        <div class="stats-card">
                            <i class="fas fa-clipboard-list"></i>
                            <span>Total reservations: ${fn:length(reservations)}</span>
                        </div>
                    </div>

                    <div class="table-container">
                        <table class="admin-table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Adherent</th>
                                    <th>Livre</th>
                                    <th>Date</th>
                                    <th>Statut</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="r" items="${reservations}">
                                    <tr>
                                        <td>${r.idReservation}</td>
                                        <td>${r.nomAdherant}</td>
                                        <td>${r.titreLivre}</td>
                                        <td>${r.dateReservation}</td>
                                        <td>
                                            <span class="status-badge ${r.statut.toLowerCase().replace(' ', '-')}">
                                                ${r.statut}
                                            </span>
                                        </td>
                                        <td>
                                            <form action="${pageContext.request.contextPath}/admin/valider"
                                                method="post">
                                                <input type="hidden" name="idReservation" value="${r.idReservation}" />
                                                <button type="submit" class="action-btn validate-btn">
                                                    <i class="fas fa-check"></i> Valider
                                                </button>
                                            </form>
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