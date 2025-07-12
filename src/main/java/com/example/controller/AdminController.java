package com.example.controller;

import com.example.model.Admin;
import com.example.model.Exemplaire;
import com.example.model.Pret;
import com.example.repository.AdherantRepository;
import com.example.repository.PretRepository;
import com.example.service.AdminService;
import com.example.service.ExemplaireService;
import com.example.service.PretService;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
        return "adminDashboard"; 
    }

    @GetMapping("/admin/formPret")
    public ModelAndView afficherFormulairePret() {
        return new ModelAndView("formPret");
    }

    @Autowired
    @Lazy
    private PretService pretService;

    @Autowired
    @Lazy
    private AdherantRepository adherantRepository;

    @Autowired
    @Lazy
    private ExemplaireService exemplaireService;

    @PostMapping("/admin/savePret")
    public ModelAndView enregistrerPret(
            @RequestParam("nomAdherant") String nomAdherant,
            @RequestParam("titreLivre") String titreLivre,
            @RequestParam("typePret") String typePret,
            @RequestParam("dateEmprunt") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateEmprunt,
            @RequestParam("dateRetour") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateRetour,
            @RequestParam("status") String status) {

        System.out.println(nomAdherant + " " + titreLivre + " " + typePret + " " + dateEmprunt.toString());
        var adherant = adherantRepository.findByNom(nomAdherant);
        if (adherant == null) {
            ModelAndView mav = new ModelAndView("formPret");
            mav.addObject("erreur", "Adhérant non trouvé pour : " + nomAdherant);
            System.out.println("null adherant");
            return mav;
        }
        Integer idAdherant = adherant.getIdAdherant();
        Exemplaire exemplaireOpt = exemplaireService.getExemplaireByLivreTitre(titreLivre);
        if (exemplaireOpt == null) {
            ModelAndView mav = new ModelAndView("formPret");
            mav.addObject("erreur", "Aucun exemplaire trouvé pour le titre : " + titreLivre);
            System.out.println("empty");
            return mav;
        }

        Integer idExemplaire = exemplaireOpt.getIdExemplaire();

        boolean success = pretService.ajouterPret(
                idAdherant, 
                idExemplaire,
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
    @Lazy
    private PretRepository pretRepository;

    @GetMapping("/admin/listePrets")
    public ModelAndView afficherListePrets() {
        List<Pret> liste = pretRepository.findAll();
        for (Pret pret : liste) {
            System.out.println(pret.getAdherant().getNom());
        }
        ModelAndView mav = new ModelAndView("listePrets");
        mav.addObject("prets", liste);
        return mav;
    }

}
