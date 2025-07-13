<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

        <html>

        <head>
            <title>Page d'accueil</title>
            <link href="${pageContext.request.contextPath}/assets/css/nav_bar.css" rel="stylesheet">
            <link href="${pageContext.request.contextPath}/assets/css/acceuil.css" rel="stylesheet">
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        </head>

        <body>
            <nav class=" navbar">
                <div class="navbar-brand">
                    <i class="fas fa-book-open"></i>
                    <span>Bibliotheque Universitaire</span>
                </div>

                <div class="navbar-links">
                    <a href="acceuil" class="nav-link active">
                        <i class="fas fa-home"></i>
                        <span>Accueil</span>
                    </a>
                    <a href="reservations" class="nav-link">
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

            <div class="content">
                <h2 class="content-title">Liste des livres</h2>

                <div class="livres-container">
                    <c:forEach var="livre" items="${livres}">
                        <div class="livre-card">
                            <div class="livre-header">
                                <h3 class="livre-titre">${livre.titreLivre}</h3>
                                <span class="livre-auteur">${livre.nomAuteur}</span>
                            </div>

                            <div class="livre-details">
                                <div class="detail-item">
                                    <i class="fas fa-tags"></i>
                                    <span>${livre.categories}</span>
                                </div>
                                <div class="detail-item">
                                    <i class="fas fa-copy"></i>
                                    <span>Exemplaires: ${livre.nombreExemplaires}</span>
                                </div>
                            </div>

                            <form action="formReservation" method="get" class="livre-actions">
                                <input type="hidden" name="idExemplaire" value="${livre.idExemplaire}" />
                                <button type="submit" class="reserver-btn">
                                    <i class="fas fa-bookmark"></i> Reserver
                                </button>
                            </form>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </body>

        </html>