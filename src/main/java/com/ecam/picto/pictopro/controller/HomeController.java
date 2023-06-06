package com.ecam.picto.pictopro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/login")
    public String goConnexion() {
        return "connexion";
    }

    @GetMapping("/register")
    public String goInscription() {
        return "inscription";
    }

    @GetMapping("/dashboard")
    public String godashboard() {
        return "dashboard";
    }
}