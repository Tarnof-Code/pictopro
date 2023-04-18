package com.ecam.picto.pictopro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PatientController {
    @GetMapping("/patients")
    public String goPatients(Model model) {
        model.addAttribute("message", "Mes patients");
        return "patients";
    }
}