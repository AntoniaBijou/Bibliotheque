<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Rendre un livre</title>
    <link href="${pageContext.request.contextPath}/assets/css/nav_bar.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/clientForms.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
    <nav class="navbar">
        <div class="navbar-brand">
            <i class="fas fa-book-open"></i>
            <span>Bibliotheque Universitaire</span>
        </div>

        <div class="navbar-links">
            <a href="acceuil" class="nav-link">
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

    <div class="form-container">
        <h2 class="form-title"><i class="fas fa-book"></i> Rendu du livre : ${pret.exemplaire.livre.titre}</h2>
        
        <c:if test="${not empty message}">
            <div class="alert-message error">
                <i class="fas fa-exclamation-circle"></i> ${message}
            </div>
        </c:if>
        
        <form class="client-form" action="${pageContext.request.contextPath}/pret/rendre/${pret.idPret}" method="post">
            <div class="form-group">
                <label class="form-label">
                    <i class="fas fa-calendar-check"></i> Date de retour reelle
                </label>
                <input type="date" name="dateRetourEffective" class="form-input" required>
            </div>
            
            <div class="form-actions">
                <button type="submit" class="submit-btn">
                    <i class="fas fa-check-circle"></i> Valider le retour
                </button>
            </div>
        </form>
    </div>
</body>
</html>