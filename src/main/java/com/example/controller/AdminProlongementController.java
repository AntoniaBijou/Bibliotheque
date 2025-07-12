package com.example.controller;

import com.example.model.Prolongement;
import com.example.model.Pret;
import com.example.service.ProlongementService;
import com.example.service.PretService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminProlongementController {

    @Autowired
    @Lazy
    private ProlongementService prolongementService;

    @Autowired
    @Lazy
    private PretService pretService;

    @GetMapping("/listeProlongements")
    public ModelAndView listeProlongements() {
        List<Prolongement> prolongements = prolongementService.listeTous();
        ModelAndView mav = new ModelAndView("listeProlongements");
        mav.addObject("prolongements", prolongements);
        return mav;
    }

@PostMapping("/prolongement/valider/{id}")
public String validerProlongement(@PathVariable("id") Integer id) {
    Prolongement prolongement = prolongementService.trouverParId(id);

    if (prolongement != null) {
        Pret pret = prolongement.getPret();
        pret.setDateRetour(prolongement.getNouvelleDateRetour());
        pretService.enregistrerPret(pret);

        prolongement.setStatut("en cours");  
        prolongementService.enregistrerProlongement(prolongement);
    }

    return "redirect:/admin/listeProlongements";
}


    @PostMapping("/prolongement/refuser/{id}")
    public String refuserProlongement(@PathVariable("id") Integer id) {
        Prolongement prolongement = prolongementService.trouverParId(id);
        if (prolongement != null) {
            prolongement.setStatut("refusé");
            prolongementService.enregistrerProlongement(prolongement);
        }
        return "redirect:/admin/listeProlongements";
    }
}
