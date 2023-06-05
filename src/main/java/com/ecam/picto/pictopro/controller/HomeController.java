package com.ecam.picto.pictopro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/login")
    public String goConnnexion() {
        return "connexion";
    }

    @GetMapping("/dashboard")
    public String godashboard() {
        return "dashboard";
    }

}