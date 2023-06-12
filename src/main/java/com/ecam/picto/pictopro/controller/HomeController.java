package com.ecam.picto.pictopro.controller;

import com.ecam.picto.pictopro.security.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private SecurityService securityService;

    @GetMapping("/")
    public String goConnexion(Model model, String error) {
        if (securityService.isAuthenticated()) {
            return "redirect:/dashboard";
        }
        if (error != null)
            model.addAttribute("error", "Vos identifiants ne sont pas valides.");
        return "connexion";
    }

    @GetMapping("/suppressionCompteSucces")
    public String goSuppressionCompteSucces() {
        return "suppressionCompteSucces";
    }

    @GetMapping("/suppressionCompteEchec")
    public String goSuppressionCompteEchec() {
        return "suppressionCompteEchec";
    }
}