package com.ecam.picto.pictopro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SessionController {
    @GetMapping("/session")
    public String goSession(Model model) {
        model.addAttribute("message", "Page Nouvelle session de travail");
        return "session";
    }
}
