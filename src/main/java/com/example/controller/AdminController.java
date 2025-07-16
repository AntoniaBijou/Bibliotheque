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
import java.time.ZoneId;
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
    public String afficherFormulairePret() {
        return "formPret";
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

        LOG.info("Tentative d'enregistrement d'un prêt pour l'adherent '{}' et le livre '{}'", nomAdherant, titreLivre);

        // Verification de l'adherent
        var adherant = adherantRepository.findByNom(nomAdherant);
        if (adherant == null) {
            LOG.warn("Adherant non trouve : {}", nomAdherant);
            redirectAttributes.addFlashAttribute("erreur", "Adherant non trouve : " + nomAdherant);
            return "redirect:/admin/formPret";
        }

        // Verification de l'exemplaire
        Exemplaire exemplaire = exemplaireService.getExemplaireByLivreTitre(titreLivre);
        if (exemplaire == null) {
            LOG.warn("Aucun exemplaire trouve pour le titre : {}", titreLivre);
            redirectAttributes.addFlashAttribute("erreur", "Aucun exemplaire trouve pour le titre : " + titreLivre);
            return "redirect:/admin/formPret";
        }

        // Tentative d'ajout du prêt
        boolean success = pretService.ajouterPret(
                adherant.getIdAdherant(),
                exemplaire.getIdExemplaire(),
                typePret,
                dateEmprunt,
                dateRetour,
                status);

        if (success) {
            LOG.info("Prêt enregistre avec succès pour l'adherent '{}' et le livre '{}'", nomAdherant, titreLivre);
            redirectAttributes.addFlashAttribute("successMessage", "Prêt enregistre avec succès.");
            return "redirect:/admin/dashboard";
        } else {
            // Verifier la raison de l'echec
            var abonnementOpt = pretService.getAbonnementRepository().findByAdherant(adherant);
            if (!abonnementOpt.isPresent()) {
                LOG.warn("Aucun abonnement trouve pour l'adherent : {}", nomAdherant);
                redirectAttributes.addFlashAttribute("erreur",
                        "Aucun abonnement actif pour l'adherent : " + nomAdherant);
            } else {
                LocalDate debutAbonnement = abonnementOpt.get().getDateDebut().toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate finAbonnement = abonnementOpt.get().getDateFin().toInstant().atZone(ZoneId.systemDefault())
                        .toLocalDate();
                if (dateEmprunt.isBefore(debutAbonnement) || dateEmprunt.isAfter(finAbonnement) ||
                        dateRetour.isBefore(debutAbonnement) || dateRetour.isAfter(finAbonnement)) {
                    LOG.warn("Les dates du prêt ({}-{}) ne sont pas dans la periode d'abonnement ({}-{})",
                            dateEmprunt, dateRetour, debutAbonnement, finAbonnement);
                    redirectAttributes.addFlashAttribute("erreur",
                            "Les dates du prêt doivent être comprises entre " + debutAbonnement + " et "
                                    + finAbonnement);
                } else if (adherant.getTypeAdherant() == null || adherant.getTypeAdherant().getNombreQuota() <= 0) {
                    LOG.warn("Quota insuffisant pour l'adherent : {}", nomAdherant);
                    redirectAttributes.addFlashAttribute("erreur",
                            "Quota de prêts epuise pour l'adherent : " + nomAdherant);
                } else {
                    LOG.error("echec de l'enregistrement du prêt pour l'adherent '{}' et le livre '{}'", nomAdherant,
                            titreLivre);
                    redirectAttributes.addFlashAttribute("erreur",
                            "echec de l'enregistrement du prêt. Verifiez les donnees saisies.");
                }
            }
            return "redirect:/admin/formPret";
        }
    }

    @GetMapping("/admin/listePrets")
    public ModelAndView afficherListePrets() {
        List<Pret> liste = pretRepository.findAll();
        LOG.info("Recuperation de {} prêts", liste.size());
        ModelAndView mav = new ModelAndView("listePrets");
        mav.addObject("prets", liste);
        return mav;
    }
}