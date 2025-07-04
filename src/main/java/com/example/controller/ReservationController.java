package com.example.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.example.model.Adherant;
import com.example.model.Exemplaire;
import com.example.model.Reservation;
import com.example.projection.LivreVueProjection;
import com.example.projection.ReservationProjection;
import com.example.repository.AdherantRepository;
import com.example.repository.ExemplaireRepository;
import com.example.repository.ReservationRepository;
import com.example.service.LivreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReservationController {

    @Autowired
    private LivreService livreService;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private AdherantRepository adherantRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    // 👉 Affichage du formulaire
    @GetMapping("/formReservation")
    public ModelAndView afficherFormulaireReservation(@RequestParam("idExemplaire") int idExemplaire) {
        ModelAndView mav = new ModelAndView("formReservation");
        mav.addObject("idExemplaire", idExemplaire);
        return mav;
    }

    // 👉 Traitement du formulaire
    @PostMapping("/reserver")
    public ModelAndView enregistrerReservation(
            @RequestParam("dateReservation") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateReservation,
            @RequestParam("statut") String statut,
            @RequestParam("nomAdherant") String nomAdherant,
            @RequestParam("idExemplaire") int idExemplaire) {

        // 1️⃣ Récupérer l’adhérant via son nom
        Adherant adherant = adherantRepository.findByNom(nomAdherant);
        if (adherant == null) {
            ModelAndView mav = new ModelAndView("formReservation");
            mav.addObject("erreur", "Adhérant introuvable !");
            mav.addObject("idExemplaire", idExemplaire);
            return mav;
        }

        // 2️⃣ Insérer la réservation
        Reservation reservation = new Reservation();
        reservation.setDateReservation(dateReservation);
        reservation.setStatut(statut);
        reservation.setIdAdherant(adherant.getIdAdherant());
        reservation.setIdExemplaire(idExemplaire);
        reservationRepository.save(reservation);

        // 3️⃣ Diminuer le nombre d’exemplaires
        Optional<Exemplaire> optional = exemplaireRepository.findById(idExemplaire);
        if (optional.isPresent()) {
            Exemplaire ex = optional.get();
            int nouveauNombre = ex.getNombre() - 1;
            ex.setNombre(Math.max(nouveauNombre, 0)); // évite négatif
            exemplaireRepository.save(ex);
        }

        // 4️⃣ Retour à la page d’accueil avec la liste à jour
        ModelAndView mav = new ModelAndView("acceuil");
        List<LivreVueProjection> livres = livreService.getLivresDepuisVue();
        mav.addObject("livres", livres);
        return mav;
    }

    @GetMapping("/reservations")
    public ModelAndView afficherReservations() {
        ModelAndView mav = new ModelAndView("reservations");
        List<ReservationProjection> liste = reservationRepository.getAllReservations();
        mav.addObject("reservations", liste);
        return mav;
    }

    

}
