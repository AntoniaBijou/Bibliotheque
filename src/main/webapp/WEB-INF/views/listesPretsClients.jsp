<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <!DOCTYPE html>
    <html>

    <head>
        <title>Liste des prets</title>

        <link href="${pageContext.request.contextPath}/assets/css/listesPretsClients.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/assets/css/nav_bar.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    </head>

    <body>

        <nav class=" navbar">
            <div class="navbar-brand">
                <i class="fas fa-book-open"></i>
                <span>Bibliotheque Universitaire</span>
            </div>

            <div class="navbar-links">
                <a href="acceuil" class="nav-link ">
                    <i class="fas fa-home"></i>
                    <span>Accueil</span>
                </a>
                <a href="reservations" class="nav-link">
                    <i class="fas fa-bookmark"></i>
                    <span>Reservations</span>
                </a>
                <a href="listes_prets_adherant" class="nav-link active">
                    <i class="fas fa-book"></i>
                    <span>Prets en cours</span>
                </a>
                <a href="admin" class="nav-link">
                    <i class="fas fa-user-shield"></i>
                    <span>Admin</span>
                </a>
            </div>
            <div class="navbar-actions">
                <form class="logout-form" action="logout" method="post">
                    <button type="submit" class="logout-btn">
                        <i class="fas fa-sign-out-alt"></i>
                        <span>Deconnexion</span>
                    </button>
                </form>
            </div>
        </nav>
        <div class="prets-container">
            <h2 class="page-title">Liste de tous les prets</h2>

            <c:if test="${not empty message}">
                <div class="success-message">
                    <i class="fas fa-check-circle"></i> ${message}
                </div>
            </c:if>

            <div class="prets-grid">
                <c:forEach var="pret" items="${prets}">
                    <div class="pret-card ${pret.status.toLowerCase()}">
                        <div class="pret-header">
                            <span class="pret-id">#${pret.idPret}</span>
                            <span class="pret-status">${pret.status}</span>
                        </div>

                        <div class="pret-body">
                            <div class="pret-info">
                                <i class="fas fa-user"></i>
                                <span>${pret.adherant.nom}</span>
                            </div>

                            <div class="pret-info">
                                <i class="fas fa-book"></i>
                                <span>${pret.exemplaire.livre.titre}</span>
                            </div>

                            <div class="pret-dates">
                                <div class="date-group">
                                    <i class="fas fa-calendar-check"></i>
                                    <div>
                                        <span class="date-label">Emprunt</span>
                                        <span class="date-value">${pret.dateEmprunt}</span>
                                    </div>
                                </div>

                                <div class="date-group">
                                    <i class="fas fa-calendar-times"></i>
                                    <div>
                                        <span class="date-label">Retour</span>
                                        <span class="date-value">${pret.dateRetour}</span>
                                    </div>
                                </div>
                            </div>

                            <div class="pret-type">
                                <i class="fas fa-tag"></i>
                                <span>${pret.typePret}</span>
                            </div>
                        </div>

                        <div class="pret-actions">
                            <c:choose>
                                <c:when test="${pret.status == 'rendu'}">
                                    <button class="action-btn disabled" disabled>
                                        <i class="fas fa-check"></i> Livre rendu
                                    </button>
                                </c:when>
                                <c:otherwise>
                                    <a href="${pageContext.request.contextPath}/pret/rendre/${pret.idPret}"
                                        class="action-btn return-btn">
                                        <i class="fas fa-undo"></i> Rendre
                                    </a>
                                    <a href="${pageContext.request.contextPath}/prolongement/demande/${pret.idPret}"
                                        class="action-btn extend-btn">
                                        <i class="fas fa-calendar-plus"></i> Prolonger
                                    </a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

    </body>

    </html>