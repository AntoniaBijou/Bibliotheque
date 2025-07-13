<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
    <html lang="fr">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Connexion - Systeme de Gestion Bibliotheque</title>
        <link href="${pageContext.request.contextPath}/assets/css/index.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    </head>

    <body>
        <div class="login-wrapper">
            <div class="login-left">
                <div class="library-brand">
                    <i class="fas fa-book-open"></i>
                    <h1>Bibliotheque Universitaire</h1>
                </div>
                <div class="library-quote">
                    <blockquote>
                        "La lecture est une amitie discrete et indulgente"
                        <cite>- Marcel Proust</cite>
                    </blockquote>
                </div>
                <div class="library-features">
                    <div class="feature">
                        <i class="fas fa-search"></i>
                        <span>Recherche avancee</span>
                    </div>
                    <div class="feature">
                        <i class="fas fa-bookmark"></i>
                        <span>Gestion des prets</span>
                    </div>
                    <div class="feature">
                        <i class="fas fa-bell"></i>
                        <span>Alertes personnalisees</span>
                    </div>
                </div>
            </div>

            <div class="login-right">
                <div class="login-container">
                    <header class="login-header">
                        <h2>Connectez-vous</h2>
                        <p>Accedez a votre espace personnel</p>
                    </header>

                    <form class="login-form" action="${pageContext.request.contextPath}/index" method="post" novalidate>
                        <div class="form-group">
                            <label for="nom">Identifiant</label>
                            <div class="input-with-icon">
                                <i class="fas fa-user"></i>
                                <input type="text" id="nom" name="nom" placeholder="Nom d'utilisateur" required>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="email">Email</label>
                            <div class="input-with-icon">
                                <i class="fas fa-envelope"></i>
                                <input type="email" id="email" name="email" placeholder="exemple@email.com" required>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="password">Mot de passe</label>
                            <div class="input-with-icon">
                                <i class="fas fa-lock"></i>
                                <input type="password" id="password" name="password" placeholder="......" required>
                            </div>
                        </div>

                        <div class="form-options">
                            <label class="checkbox-container">
                                <input type="checkbox" id="remember" name="remember">
                                <span class="checkmark"></span>
                                Se souvenir de moi
                            </label>
                            <a href="#" class="forgot-password">Mot de passe oublie ?</a>
                        </div>

                        <button type="submit" class="btn-login">
                            <i class="fas fa-sign-in-alt"></i> Se connecter
                        </button>

                        <div class="login-footer">
                            <p>Premiere visite ? <a href="#">Creer un compte</a></p>
                            <p class="footer-info">
                                <small> 2025 Systeme de Gestion Bibliotheque. Tous droits reserves.</small>
                            </p>
                        </div>
                    </form>

                    <c:if test="${not empty erreur}">
                        <div class="error-message">
                            <i class="fas fa-exclamation-circle"></i>
                            <span>${erreur}</span>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>


    </body>

    </html>