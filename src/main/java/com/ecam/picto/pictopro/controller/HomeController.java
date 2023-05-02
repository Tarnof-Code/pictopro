package com.ecam.picto.pictopro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String goConnnexionInscription(Model model) {
        model.addAttribute("message", "Page Connexion/Inscription");
        return "connexionInscription";
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }


}