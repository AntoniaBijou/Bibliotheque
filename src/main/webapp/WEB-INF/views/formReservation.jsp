<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <html>

    <head>
        <title>Reservation</title>
        <link href="${pageContext.request.contextPath}/assets/css/formReservation.css" rel="stylesheet">
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

        <div class="reservation-container">
            <h2 class="form-title">Formulaire de reservation</h2>

            <form class="reservation-form" action="reserver" method="post">
                <input type="hidden" name="idExemplaire" value="${idExemplaire}" />

                <div class="form-group">
                    <label for="nomAdherant" class="form-label">
                        <i class="fas fa-user"></i> Nom de l'adherent
                    </label>
                    <input type="text" id="nomAdherant" name="nomAdherant" class="form-input" required>
                </div>

                <div class="form-group">
                    <label for="dateReservation" class="form-label">
                        <i class="fas fa-calendar-alt"></i> Date de reservation
                    </label>
                    <input type="date" id="dateReservation" name="dateReservation"
                        value="<%= java.time.LocalDate.now() %>" class="form-input" required>
                </div>

                <div class="form-group">
                    <label for="statut" class="form-label">
                        <i class="fas fa-info-circle"></i> Statut
                    </label>
                    <select id="statut" name="statut" class="form-select">
                        <option value="en attente">En attente</option>
                        <option value="confirmee">Confirmee</option>
                        <option value="annulee">Annulee</option>
                    </select>
                </div>

                <div class="form-actions">
                    <button type="submit" class="submit-btn">
                        <i class="fas fa-check"></i> Valider la reservation
                    </button>
                </div>
            </form>
        </div>
    </body>

    </html>