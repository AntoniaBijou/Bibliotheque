<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des prets - Admin</title>
    <link href="${pageContext.request.contextPath}/assets/css/adminDashboard.css" rel="stylesheet">
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
            background-color: rgba(0,0,0,0.5);
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
                    padding: 5px 7px;
                    height: 30px;
                    width: auto;
                    border-radius: 10px;
                    border: none;
                    background-color: #e74c3c;
                    color: #f8f9fa;
                }
    </style>
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
                            <th>Action</th>
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
                                <td>
                                    <a href="#" class="info-btn" data-id="${pret.adherant.idAdherant}">
                                        <button type="button" class="voir_details"><i class="fas fa-info-circle"></i> Voir info</button>
                                        
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <div id="adherantModal" class="modal">
        <div class="modal-content">
            <span class="close"><i class="fas fa-times"></i></span>
            <h2>Informations de l'adherent</h2>
            <p><strong>Nom :</strong> <span id="adherantNom"></span></p>
            <p><strong>Type :</strong> <span id="adherantType"></span></p>
            <p><strong>Nombre Quota :</strong> <span id="adherantQuota"></span></p>
            <p><strong>Date debut d'abonnement:</strong> <span id="abonnementDateDebut"></span></p>
            <p><strong>Date fin d'abonnement:</strong> <span id="abonnementDateFin"></span></p>
        </div>
    </div>

    <script>
        $(document).ready(function() {
            $('.info-btn').click(function(e) {
                e.preventDefault();
                var id = $(this).data('id');
                $.ajax({
                    url: '${pageContext.request.contextPath}/api/adherant/' + id + '/abonnements',
                    type: 'GET',
                    dataType: 'json',
                    success: function(data) {
                        if (data && data.length > 0) {
                            var abonnement = data[0];
                            $('#adherantNom').text(abonnement.nomAdherant);
                            $('#adherantType').text(abonnement.typeAdherant);
                            $('#adherantQuota').text(abonnement.nombreQuota);
                            $('#abonnementDateDebut').text(new Date(abonnement.dateDebut).toLocaleDateString());
                            $('#abonnementDateFin').text(new Date(abonnement.dateFin).toLocaleDateString());
                            $('#adherantModal').show();
                        } else {
                            alert('Aucun abonnement trouve pour cet adherent.');
                        }
                    },
                    error: function(xhr, status, error) {
                        alert('Erreur lors de la recuperation des details : ' + xhr.responseText);
                    }
                });
            });

            $('.close').click(function() {
                $('#adherantModal').hide();
            });

            $(window).click(function(event) {
                if (event.target == $('#adherantModal')[0]) {
                    $('#adherantModal').hide();
                }
            });
        });
    </script>
</body>
</html>