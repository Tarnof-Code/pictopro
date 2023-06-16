package com.ecam.picto.pictopro.controller;

import com.ecam.picto.pictopro.security.UserValidator;
import com.ecam.picto.pictopro.entity.Professionnel;
import com.ecam.picto.pictopro.security.services.SecurityService;
import com.ecam.picto.pictopro.service.ProfessionnelService;
import com.ecam.picto.pictopro.service.VerificationTokenService;
import com.ecam.picto.pictopro.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Controller
public class InscriptionController {
    @Autowired
    private ProfessionnelService professionnelService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private VerificationTokenService verificationTokenService;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private TemplateEngine templateEngine;

    @GetMapping("/register")
    public String registration(Model model) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }
        model.addAttribute("userForm", new Professionnel());

        return "inscription";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") Professionnel userForm, BindingResult bindingResult, HttpServletRequest request) throws RuntimeException {
        try {
            userValidator.validate(userForm, bindingResult);

            if (bindingResult.hasErrors()) {
                return "inscription";
            }
            // Generate a verification token
            String token = verificationTokenService.generateToken();

            // Save the verification token for the user
            verificationTokenService.saveVerificationToken(userForm, token);

            // Send the verification email with the token
            sendVerificationEmail(userForm.getEmail(), token, request);

//            securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

            professionnelService.savePro(userForm);
            return "redirect:/inscriptionSucces";
        } catch (RuntimeException e) {
            return "redirect:/inscriptionEchec";
        }
    }

    public void sendVerificationEmail(String email, String token, HttpServletRequest request) {
        String subject = "Activation du compte PictoPicto";
        String verificationLink = Utility.getSiteURL(request) + "/verify/" + token;

        // Create a Thymeleaf context and add the variables needed in the template
        Context context = new Context();
        context.setVariable("verificationLink", verificationLink);

        // Process the Thymeleaf template with the context
        String emailContent = templateEngine.process("verification.html", context);

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(emailContent, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
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


