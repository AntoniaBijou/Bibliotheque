<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

        <!DOCTYPE html>
        <html>

        <head>
            <title>Formulaire de pret - Admin</title>
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
                        <li class="active">
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

                <div class="admin-main">
                    <div class="form-container">
                        <h2 class="form-title"><i class="fas fa-hand-holding"></i> Nouveau pret</h2>
                        <c:if test="${not empty erreur}">
                            <p style="color: red;">${erreur}</p>
                        </c:if>
                        <c:if test="${not empty successMessage}">
                            <p style="color: green;">${successMessage}</p>
                        </c:if>

                        <form class="admin-form" action="${pageContext.request.contextPath}/admin/savePret"
                            method="post">
                            <div class="form-group">
                                <label class="form-label">
                                    <i class="fas fa-user"></i> Nom de l'adherent
                                </label>
                                <input type="text" name="nomAdherant" class="form-input" required>
                            </div>

                            <div class="form-group">
                                <label class="form-label">
                                    <i class="fas fa-book"></i> Titre du livre
                                </label>
                                <input type="text" name="titreLivre" class="form-input" required>
                            </div>

                            <div class="form-group">
                                <label class="form-label">
                                    <i class="fas fa-tag"></i> Type de pret
                                </label>
                                <select name="typePret" class="form-select">
                                    <option value="sur place">Sur place</option>
                                    <option value="a domicile">A domicile</option>
                                </select>
                            </div>

                            <div class="form-row">
                                <div class="form-group">
                                    <label class="form-label">
                                        <i class="fas fa-calendar-check"></i> Date d'emprunt
                                    </label>
                                    <input type="date" name="dateEmprunt" class="form-input" required>
                                </div>

                                <div class="form-group">
                                    <label class="form-label">
                                        <i class="fas fa-calendar-times"></i> Date de retour
                                    </label>
                                    <input type="date" name="dateRetour" class="form-input" required>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="form-label">
                                    <i class="fas fa-info-circle"></i> Statut
                                </label>
                                <select name="status" class="form-select">
                                    <option value="en cours">En cours</option>
                                    <option value="retourne">Retourne</option>
                                </select>
                            </div>

                            <div class="form-actions">
                                <button type="submit" class="submit-btn">
                                    <i class="fas fa-check"></i> Valider le pret
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </body>

        </html>