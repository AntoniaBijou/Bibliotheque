package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.Adherant;
import com.example.service.AdherantService;
import com.example.service.LivreService;

@Controller
public class LoginController {
    @Autowired
    private AdherantService adherantService;

    @Autowired
    private LivreService livreService;

    @GetMapping("/")
    public String afficherFormulaire() {
        return "/index";
    }

    @GetMapping("/index")
    public String afficherFormulaireLogin() {
        return "/index";
    }

    @PostMapping("/index")
    public ModelAndView traiterLogin(@RequestParam("nom") String nom,
            @RequestParam("email") String email,
            @RequestParam("password") String password) {
        ModelAndView mav = new ModelAndView();
        Adherant adherant = adherantService.verifierConnexion(nom, email, password);

        if (adherant != null) {
            mav.setViewName("/acceuil");
            mav.addObject("livres", livreService.getLivresDepuisVue());
        } else {
            mav.setViewName("/index");
            mav.addObject("erreur", "Identifiants incorrects.");
        }

        return mav;
    }
      @GetMapping("/acceuil")
    public String Acceuil() {
        return "/acceuil"; 
    }



    @PostMapping("/logout")
    public String seDeconnecter(HttpSession session) {
        session.invalidate(); 
        return "redirect:/index";
    }

}
