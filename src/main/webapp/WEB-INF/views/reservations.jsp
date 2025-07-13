<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <html>

    <head>
        <title>Liste des Reservations</title>
        <link href="${pageContext.request.contextPath}/assets/css/reservation.css" rel="stylesheet">
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
                <a href="acceuil" class="nav-link">
                    <i class="fas fa-home"></i>
                    <span>Accueil</span>
                </a>
                <a href="reservations" class="nav-link active">
                    <i class="fas fa-bookmark"></i>
                    <span>Reservations</span>
                </a>
                <a href="listes_prets_adherant" class="nav-link">
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
        <div class="reservations-container">
            <h2 class="page-title">Liste des Reservations</h2>

            <div class="reservations-list">
                <c:forEach var="r" items="${reservations}">
                    <div class="reservation-card">
                        <div class="reservation-header">
                            <span class="reservation-id">#${r.idReservation}</span>
                            <span class="reservation-status ${r.statut.toLowerCase().replace(' ', '-')}">
                                ${r.statut}
                            </span>
                        </div>

                        <div class="reservation-body">
                            <div class="reservation-info">
                                <i class="fas fa-user"></i>
                                <span>${r.nomAdherant}</span>
                            </div>

                            <div class="reservation-info">
                                <i class="fas fa-book"></i>
                                <span>${r.titreLivre}</span>
                            </div>

                            <div class="reservation-info">
                                <i class="fas fa-calendar-alt"></i>
                                <span>${r.dateReservation}</span>
                            </div>
                        </div>

                        <div class="reservation-actions">
                            <c:if test="${r.statut == 'en attente'}">
                                <button class="action-btn confirm-btn">
                                    <i class="fas fa-check"></i> Confirmer
                                </button>
                                <button class="action-btn cancel-btn">
                                    <i class="fas fa-times"></i> Annuler
                                </button>
                            </c:if>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </body>

    </html>