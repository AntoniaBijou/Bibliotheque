package com.example.controller;

import com.example.model.Admin;
import com.example.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
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
}
