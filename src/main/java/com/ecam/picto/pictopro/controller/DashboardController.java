package com.ecam.picto.pictopro.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication authentication) {
        String username = authentication.getName();
        // Determine the appropriate dashboard page based on the user role
        authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", username);
        if (authentication != null && authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
            return "admin_dashboard";
        } else {
            return "pro_dashboard";
        }
    }
}