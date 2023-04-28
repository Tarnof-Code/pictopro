package com.ecam.picto.pictopro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CompteController {
    @GetMapping("/compte")
    public String goCompte(Model model) {
        model.addAttribute("message", "Mon compte");
        model.addAttribute("module","compte");
        return "compte";
    }
}
