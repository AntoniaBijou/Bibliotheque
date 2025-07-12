package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.Pret;
import com.example.repository.PretRepository;

@Controller
public class PretController {

    @Autowired
    @Lazy
    private PretRepository pretRepository;

    
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
    
}
