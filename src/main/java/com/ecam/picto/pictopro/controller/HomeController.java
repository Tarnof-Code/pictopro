package com.ecam.picto.pictopro.controller;

import com.ecam.picto.pictopro.security.services.SecurityService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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
            model.addAttribute("error", "Vos identifiants ne sont pas valides");
        return "connexion";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";
    }

    @GetMapping("/mentions")
    public String goMentions() {
        return "mentions";
    }

    @GetMapping("/partners")
    public String goPartners() {
        return "partners";
    }

    @GetMapping("/a_propos")
    public String goAPropos() {
        return "a_propos";
    }

    @GetMapping("/suppressionCompteSucces")
    public String goSuppressionCompteSucces() {
        return "suppressionCompteSucces";
    }

    @GetMapping("/suppressionCompteEchec")
    public String goSuppressionCompteEchec() {
        return "suppressionCompteEchec";
    }

    @GetMapping("/modificationCompteSucces")
    public String goModificationCompteSucces() {
        return "modificationCompteSucces";
    }

    @GetMapping("/modificationCompteEchec")
    public String goModificationCompteEchec() {
        return "modificationCompteEchec";
    }
}