package com.ecam.picto.pictopro.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
//@Controller
@RequestMapping("/dashboard")
public class DashboardController {

//    @GetMapping("/dashboard")
//    public ResponseEntity<String> dashboard() {
//        // Implement your dashboard logic here
//        return ResponseEntity.ok("Welcome to the user dashboard!");
//    }

    @GetMapping("/admin")
    public String showAdminDashboard(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        return "dashboard";
    }

    @GetMapping("/pro")
    public String showProDashboard(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());
        return "dashboard";
    }

}