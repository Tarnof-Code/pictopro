package com.ecam.picto.pictopro.controller;

import com.ecam.picto.pictopro.UserValidator;
import com.ecam.picto.pictopro.entity.Professionnel;
import com.ecam.picto.pictopro.security.services.SecurityService;
import com.ecam.picto.pictopro.service.ProfessionnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InscriptionController {
    @Autowired
    private ProfessionnelService professionnelService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;
    @GetMapping("/register")
    public String registration(Model model) {
        if (securityService.isAuthenticated()) {
            return "redirect:/login";
        }
        model.addAttribute("userForm", new Professionnel());

        return "inscription";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") Professionnel userForm, BindingResult bindingResult) throws RuntimeException{
        try {
            userValidator.validate(userForm, bindingResult);

            if (bindingResult.hasErrors()) {
                return "inscription";
            }
            professionnelService.save(userForm);

            securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

            return "redirect:/inscriptionSucces";
        } catch (RuntimeException e){
            return "redirect:/inscriptionEchec";
        }
    }

    @GetMapping("/inscriptionSucces")
    public String goInscriptionSucces() {
        return "inscriptionSucces";
    }

    @GetMapping("/inscriptionEchec")
    public String goInscriptionEchec() {
        return "inscriptionEchec";
    }
}
