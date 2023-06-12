package com.ecam.picto.pictopro.controller;

import com.ecam.picto.pictopro.entity.Professionnel;
import com.ecam.picto.pictopro.service.ProfessionnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class CompteController {
    @Autowired
    private ProfessionnelService professionnelService;
    @GetMapping("/compte")
    public String goCompte(Model model, Principal principal) {
        String username = principal.getName();
        Professionnel user = professionnelService.findByUsername(username);
        model.addAttribute("module", "compte");
        model.addAttribute("user", user);
        return "compte";
    }

}

