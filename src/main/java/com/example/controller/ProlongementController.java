package com.example.controller;

import com.example.model.Pret;
import com.example.model.Prolongement;
import com.example.service.PretService;
import com.example.service.ProlongementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/prolongement")
public class ProlongementController {

    @Autowired
    @Lazy
    private ProlongementService prolongementService;

    @Autowired
    @Lazy
    private PretService pretService;

    @GetMapping("/demande/{idPret}")
    public String afficherFormulaire(@PathVariable("idPret") Integer idPret, Model model) {
        Pret pret = pretService.trouverParId(idPret);
        Prolongement prolongement = new Prolongement();
        prolongement.setPret(pret);

        model.addAttribute("prolongement", prolongement);
        return "prolongement_formulaire";
    }

    @PostMapping("/enregistrer")
    public String enregistrerDemande(
            @RequestParam("idPret") Integer idPret,
            @RequestParam("motif") String motif,
            @RequestParam("nouvelleDateRetour") String nouvelleDateRetourStr) {

        System.out.println(" Formulaire reçu !");
        System.out.println("idPret = " + idPret);
        System.out.println("motif = " + motif);
        System.out.println("nouvelleDateRetour = " + nouvelleDateRetourStr);

        Pret pret = pretService.trouverParId(idPret);
        if (pret == null) {
            System.out.println(" Le prêt est introuvable !");
            return "redirect:/client/prets";
        }

        Prolongement prolongement = new Prolongement();
        prolongement.setDateDemande(LocalDate.now());
        prolongement.setNouvelleDateRetour(LocalDate.parse(nouvelleDateRetourStr));
        prolongement.setMotif(motif);
        prolongement.setStatut("en attente");
        prolongement.setPret(pret);

        prolongementService.enregistrerProlongement(prolongement);
        return "redirect:/client/prets";
    }

}
