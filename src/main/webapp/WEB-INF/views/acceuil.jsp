<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

        <html>

        <head>
            <title>Page d'accueil</title>
            <link href="${pageContext.request.contextPath}/assets/css/nav_bar.css" rel="stylesheet">
            <link href="${pageContext.request.contextPath}/assets/css/acceuil.css" rel="stylesheet">
            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
            <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
            <style>
                .modal {
                    display: none;
                    position: fixed;
                    top: 0;
                    left: 0;
                    width: 100%;
                    height: 100%;
                    background-color: rgba(0, 0, 0, 0.5);
                    z-index: 1000;
                }

                .modal-content {
                    background-color: white;
                    margin: 15% auto;
                    padding: 20px;
                    border: 1px solid #888;
                    width: 80%;
                    max-width: 500px;
                    border-radius: 5px;
                }

                .close {
                    color: #aaa;
                    float: right;
                    font-size: 28px;
                    font-weight: bold;
                }

                .close:hover,
                .close:focus {
                    color: black;
                    text-decoration: none;
                    cursor: pointer;
                }

                .voir_details {
                    padding: 5px 10px;
                    height: 40px;
                    width: auto;
                    border-radius: 10px;
                    border: none;
                    background-color: #e74c3c;
                    color: #f8f9fa;
                }
            </style>
        </head>

        <body>
            <nav class="navbar">
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
                                <div class="detail-item">
                                    <span>
                                        <a href="#" class="details-btn" data-id="${livre.idExemplaire}">
                                            <button type="button" class="voir_details">Voir details</button>
                                        </a>
                                    </span>
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

            <div id="livreModal" class="modal">
                <div class="modal-content">
                    <span class="close"><i class="fas fa-times"></i></span>
                    <h2>Details du livre</h2>
                    <p><strong>Titre :</strong> <span id="livreTitre"></span></p>
                    <p><strong>Auteur :</strong> <span id="livreAuteur"></span></p>
                    <p><strong>ISBN :</strong> <span id="livreIsbn"></span></p>
                    <p><strong>editeur :</strong> <span id="livreEditeur"></span></p>
                    <p><strong>Exemplaires disponibles:</strong> <span id="livreExemplaires"></span></p>
                    <p><strong>Categories :</strong> <span id="livreCategories"></span></p>
                </div>
            </div>

            <script>
                $(document).ready(function () {
                    $('.details-btn').click(function (e) {
                        e.preventDefault();
                        var id = $(this).data('id');
                        $.ajax({
                            url: '${pageContext.request.contextPath}/api/livre/' + id,
                            type: 'GET',
                            dataType: 'json',
                            success: function (data) {
                                $('#livreTitre').text(data.titre);
                                $('#livreAuteur').text(data.nomAuteur);
                                $('#livreIsbn').text(data.isbn);
                                $('#livreEditeur').text(data.editeur);
                                $('#livreExemplaires').text(data.nombreExemplaires);
                                $('#livreCategories').text(data.categories.join(', '));
                                $('#livreModal').show();
                            },
                            error: function (xhr, status, error) {
                                alert('Erreur lors de la recuperation des details : ' + xhr.responseText);
                            }
                        });
                    });

                    $('.close').click(function () {
                        $('#livreModal').hide();
                    });

                    $(window).click(function (event) {
                        if (event.target == $('#livreModal')[0]) {
                            $('#livreModal').hide();
                        }
                    });
                });
            </script>
        </body>

        </html>