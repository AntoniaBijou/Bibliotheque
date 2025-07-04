package com.example.controller;

import com.example.model.Admin;
import com.example.model.Pret;
import com.example.repository.PretRepository;
import com.example.service.AdminService;
import com.example.service.PretService;

import java.time.LocalDate;
import java.util.List;

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

    @PostMapping("/admin/savePret")
    public ModelAndView enregistrerPret(
            @RequestParam("nomAdherant") String nomAdherant,
            @RequestParam("titreLivre") String titreLivre,
            @RequestParam("typePret") String typePret,
            @RequestParam("dateEmprunt") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateEmprunt,
            @RequestParam("dateRetour") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateRetour,
            @RequestParam("status") String status) {

        System.out.println("➡️ Formulaire reçu : " + nomAdherant + ", " + titreLivre);

        boolean success = pretService.ajouterPret(nomAdherant, titreLivre, typePret, dateEmprunt, dateRetour, status);

        if (!success) {
            System.out.println("⚠️ Échec insertion : Adhérant ou livre introuvable");
            ModelAndView mav = new ModelAndView("formPret");
            mav.addObject("erreur", "Adhérant ou livre introuvable.");
            return mav;
        }

        System.out.println("✅ Prêt inséré avec succès !");
        return new ModelAndView("redirect:/admin/dashboard");
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
