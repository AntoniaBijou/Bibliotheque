package com.example.controller;

import com.example.model.Admin;
import com.example.model.Exemplaire;
import com.example.model.Pret;
import com.example.repository.AdherantRepository;
import com.example.repository.ExemplaireRepository;
import com.example.repository.LivreRepository;
import com.example.repository.PretRepository;
import com.example.service.AdminService;
import com.example.service.PretService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/admin")
    public String voirAdmin() {
        return "/admin";
    }

    @PostMapping("/admin")
    public ModelAndView verifierAdmin(
            @RequestParam("nom") String nom,
            @RequestParam("email") String email,
            @RequestParam("password") String password) {

        ModelAndView mav = new ModelAndView();
        Admin admin = adminService.verifierConnexion(nom, email, password);

        if (admin != null) {
            mav.setViewName("redirect:/dashboard");
        } else {
            mav.setViewName("/admin");
            mav.addObject("erreur", "Identifiants incorrects.");
        }

        return mav;
    }

    @GetMapping("/dashboard")
    public String voirDashboard() {
        return "adminDashboard"; // ta vue JSP adminDashboard.jsp
    }

    @GetMapping("/admin/formPret")
    public ModelAndView afficherFormulairePret() {
        return new ModelAndView("formPret");
    }

    @Autowired
    private PretService pretService;

    @Autowired
    private AdherantRepository adherantRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @PostMapping("/admin/savePret")
    public ModelAndView enregistrerPret(
            @RequestParam("nomAdherant") String nomAdherant,
            @RequestParam("titreLivre") String titreLivre,
            @RequestParam("typePret") String typePret,
            @RequestParam("dateEmprunt") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateEmprunt,
            @RequestParam("dateRetour") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateRetour,
            @RequestParam("status") String status) {

        // Conversion des noms en IDs
        var adherant = adherantRepository.findByNom(nomAdherant);
        if (adherant == null) {
            ModelAndView mav = new ModelAndView("formPret");
            mav.addObject("erreur", "Adhérant non trouvé pour : " + nomAdherant);
            return mav;
        }
        Integer idAdherant = adherant.getIdAdherant();
        Optional<Exemplaire> exemplaireOpt = exemplaireRepository.findByLivreTitre(titreLivre);
        if (exemplaireOpt.isEmpty()) {
            ModelAndView mav = new ModelAndView("formPret");
            mav.addObject("erreur", "Aucun exemplaire trouvé pour le titre : " + titreLivre);
            return mav;
        }
        Integer idExemplaire = exemplaireOpt.get().getIdExemplaire();

        boolean success = pretService.ajouterPret(
                idAdherant, // Integer
                idExemplaire, // Integer
                typePret,
                dateEmprunt,
                dateRetour,
                status);
        if (!success) {
            System.out.println("ÉCHEC - Raisons possibles:");
            System.out.println("1. Adhérant non trouvé");
            System.out.println("2. Exemplaire non trouvé");
            System.out.println("3. Erreur de validation");
        }

        return new ModelAndView(success ? "redirect:/admin/dashboard" : "formPret")
                .addObject("erreur", success ? null : "Échec de l'ajout du prêt");
    }

    @Autowired
    private PretRepository pretRepository;

    @GetMapping("/admin/listePrets")
    public ModelAndView afficherListePrets() {
        List<Pret> liste = pretRepository.findAll();
        ModelAndView mav = new ModelAndView("listePrets");
        mav.addObject("prets", liste);
        return mav;
    }
}
