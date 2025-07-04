package com.example.controller;

import com.example.model.Reservation;
import com.example.projection.ReservationProjection;
import com.example.repository.ReservationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping("/dashboard")
    public ModelAndView afficherDashboardAdmin() {
        ModelAndView mav = new ModelAndView("adminDashboard");
        List<ReservationProjection> reservations = reservationRepository.getAllReservations();

        System.out.println("Nombre de réservations trouvées : " + reservations.size());
        for (ReservationProjection r : reservations) {
            System.out.println("Reservation : " + r.getTitreLivre() + " par " + r.getNomAdherant());
        }

        mav.addObject("reservations", reservations);
        return mav;
    }

    // 🔸 Valider une réservation
    @PostMapping("/valider")
    public String validerReservation(@RequestParam("idReservation") Integer id) {
        Optional<Reservation> optional = reservationRepository.findById(id);
        if (optional.isPresent()) {
            Reservation reservation = optional.get();
            reservation.setStatut("Validée");
            reservationRepository.save(reservation);
        }
        return "redirect:/admin/dashboard";
    }

    // 🔸 Déconnexion simple
    @PostMapping("/logout")
    public String logout(javax.servlet.http.HttpSession session) {
        session.invalidate();
        return "redirect:/admin";
    }

@PostMapping("/admin/valider")
public ModelAndView validerReservation(@RequestParam("idReservation") int idReservation) {
    Optional<Reservation> optional = reservationRepository.findById(idReservation);
    if (optional.isPresent()) {
        Reservation r = optional.get();
        r.setStatut("confirme"); // ✅ PAS "Validée"
        reservationRepository.save(r);
    }

    List<ReservationProjection> liste = reservationRepository.getAllReservations();
    ModelAndView mav = new ModelAndView("reservations");
    mav.addObject("reservations", liste);
    return mav;
}


}
