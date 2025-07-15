package com.example.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.model.Exemplaire;
import com.example.model.Pret;
import com.example.repository.PretRepository;
import com.example.service.ExemplaireService;
import com.example.service.PretService;

@Controller
public class PretController {

    @Autowired
    @Lazy
    private PretRepository pretRepository;

    @Autowired
    @Lazy
    private PretService pretService;

    @Autowired
    @Lazy
    private ExemplaireService exemplaireService;

    @GetMapping("/listes_prets_adherant")
    public ModelAndView afficherListePretsAdherants() {
        List<Pret> liste = pretRepository.findAll();
        for (Pret pret : liste) {
            System.out.println(pret.getAdherant().getNom());
        }
        ModelAndView mav = new ModelAndView("listesPretsClients");
        mav.addObject("prets", liste);
        return mav;
    }

    @GetMapping("/pret/rendre/{idPret}")
    public ModelAndView afficherFormulaireRendu(@PathVariable("idPret") Integer idPret) {
        Pret pret = pretService.trouverParId(idPret);
        ModelAndView mav = new ModelAndView("rendrePret");
        mav.addObject("pret", pret);
        return mav;
    }

    @PostMapping("/pret/rendre/{idPret}")
    public String traiterRendu(@PathVariable("idPret") Integer idPret,
            @RequestParam("dateRetourEffective") String dateRetourEffectiveStr,
            RedirectAttributes redirectAttributes) {

        Pret pret = pretService.trouverParId(idPret);
        LocalDate dateRetourEffective = LocalDate.parse(dateRetourEffectiveStr);
        LocalDate dateRetourPrevue = pret.getDateRetour();

        if (dateRetourEffective.isAfter(dateRetourPrevue)) {
            redirectAttributes.addFlashAttribute("message", "Sanction : retour en retard !");
        } else {
            redirectAttributes.addFlashAttribute("message", "Livre rendu avec succès !");
        }

        pret.setStatus("rendu");
        pretService.enregistrerPret(pret);

        Exemplaire exemplaire = pret.getExemplaire();
        exemplaire.setNombre(exemplaire.getNombre() + 1);
        exemplaireService.enregistrer(exemplaire);

        return "redirect:/listes_prets_adherant";
    }
}