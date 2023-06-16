package com.ecam.picto.pictopro.controller;

import com.ecam.picto.pictopro.entity.Professionnel;
import com.ecam.picto.pictopro.service.ProfessionnelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostMapping("/updateCompte")
    public String save(@Valid @ModelAttribute("user") Professionnel useradminpro, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                return "compte";
            }

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            if (userDetails.getAuthorities().stream().anyMatch(role -> role.getAuthority().equals("ROLE_ADMIN"))) {
                // Logic for admin user
                professionnelService.updateAdmin(useradminpro);
            } else {
                // Logic for pro user
                professionnelService.updatePro(useradminpro);
            }

            return "redirect:/modificationCompteSucces";
        } catch (Exception e) {
            return "redirect:/modificationCompteEchec";
        }
    }

    @GetMapping("/deleteCompte/{id}")
    public String deleteCompteById(@PathVariable(name = "id") int id) throws RuntimeException {
        try {
            professionnelService.deleteCompteById(id);
            return "redirect:/suppressionCompteSucces";
        } catch (RuntimeException e) {
            return "redirect:/suppressionCompteEchec";
        }
    }
}

