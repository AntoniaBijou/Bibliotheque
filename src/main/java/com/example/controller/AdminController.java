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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class AdminController {

    private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    @Autowired
    @Lazy
    private PretService pretService;

    @Autowired
    @Lazy
    private AdherantRepository adherantRepository;

    @Autowired
    @Lazy
    private ExemplaireService exemplaireService;

    @Autowired
    @Lazy
    private PretRepository pretRepository;

    @GetMapping("/admin")
    public String voirAdmin() {
        return "admin";
    }

    @PostMapping("/admin")
    public ModelAndView verifierAdmin(
            @RequestParam("nom") String nom,
            @RequestParam("email") String email,
            @RequestParam("password") String password) {

        ModelAndView mav = new ModelAndView();
        Admin admin = adminService.verifierConnexion(nom, email, password);

        if (admin != null) {
            mav.setViewName("redirect:/admin/dashboard");
        } else {
            mav.setViewName("/admin");
            mav.addObject("erreur", "Identifiants incorrects.");
        }

        return mav;
    }


    @GetMapping("/admin/formPret")
    public ModelAndView afficherFormulairePret() {
        return new ModelAndView("formPret");
    }

    @PostMapping("/admin/savePret")
    public String enregistrerPret(
            @RequestParam("nomAdherant") String nomAdherant,
            @RequestParam("titreLivre") String titreLivre,
            @RequestParam("typePret") String typePret,
            @RequestParam("dateEmprunt") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateEmprunt,
            @RequestParam("dateRetour") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateRetour,
            @RequestParam("status") String status,
            RedirectAttributes redirectAttributes) {

        LOG.info("Tentative d'enregistrement d'un prêt pour l'adhérent '{}' et le livre '{}'", nomAdherant, titreLivre);

        var adherant = adherantRepository.findByNom(nomAdherant);
        if (adherant == null) {
            LOG.warn("Adhérant non trouvé : {}", nomAdherant);
            redirectAttributes.addFlashAttribute("erreur", "Adhérant non trouvé pour : " + nomAdherant);
            return "redirect:/admin/formPret";
        }

        Exemplaire exemplaire = exemplaireService.getExemplaireByLivreTitre(titreLivre);
        if (exemplaire == null) {
            LOG.warn("Aucun exemplaire trouvé pour le titre : {}", titreLivre);
            redirectAttributes.addFlashAttribute("erreur", "Aucun exemplaire trouvé pour le titre : " + titreLivre);
            return "redirect:/admin/formPret";
        }

        boolean success = pretService.ajouterPret(
                adherant.getIdAdherant(),
                exemplaire.getIdExemplaire(),
                typePret,
                dateEmprunt,
                dateRetour,
                status);

        if (success) {
            LOG.info("Prêt enregistré avec succès pour l'adhérent '{}' et le livre '{}'", nomAdherant, titreLivre);
            redirectAttributes.addFlashAttribute("successMessage", "Prêt enregistré avec succès.");
        } else {
            LOG.error("Échec de l'enregistrement du prêt pour l'adhérent '{}' et le livre '{}'", nomAdherant, titreLivre);
            redirectAttributes.addFlashAttribute("erreur", "Échec de l'enregistrement du prêt. Vérifiez les données saisies.");
        }

        return "redirect:/admin/dashboard";
    }

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