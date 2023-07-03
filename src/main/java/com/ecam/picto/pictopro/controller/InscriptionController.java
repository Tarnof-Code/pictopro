package com.ecam.picto.pictopro.controller;

import com.ecam.picto.pictopro.entity.Professionnel;
import com.ecam.picto.pictopro.security.ReCaptchaResponse;
import com.ecam.picto.pictopro.security.UserValidator;
import com.ecam.picto.pictopro.security.services.SecurityService;
import com.ecam.picto.pictopro.service.ProfessionnelService;
import com.ecam.picto.pictopro.service.VerificationTokenService;
import com.ecam.picto.pictopro.utility.Utility;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

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
    @Autowired
    private RestTemplate restTemplate;

    private String message;

    @GetMapping("/register")
    public String registration(Model model) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }
        if (message != null && !message.isEmpty()) {
            model.addAttribute("message", message);
        }
        model.addAttribute("userForm", new Professionnel());
        return "inscription";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") Professionnel userForm, BindingResult bindingResult, HttpServletRequest request, @RequestParam(name="g-recaptcha-response") String captchaResponse) throws RuntimeException {
        try {
            userValidator.validate(userForm, bindingResult);

            if (bindingResult.hasErrors()) {
                return "inscription";
            }
            String url = "https://www.google.com/recaptcha/api/siteverify";
            String params = "?secret=6LerQO0mAAAAADP0x9YCHpENyrlbf2ji9KF8O6yy&response="+captchaResponse;
            ReCaptchaResponse reCaptchaResponse = restTemplate.exchange(url+params, HttpMethod.POST, null, ReCaptchaResponse.class).getBody();
            if(reCaptchaResponse.isSuccess()){

                // On génère un token de vérification
                String token = verificationTokenService.generateToken();

                // On sauvegarde le token de vérification
                verificationTokenService.saveVerificationToken(userForm, token);

                // On envoie l'email de vérification avec le token
                sendVerificationEmail(userForm.getEmail(), token, request);

                professionnelService.savePro(userForm);

                return "redirect:/inscriptionSucces";
            } else {
                message = "SVP Vérifiez le CAPTCHA";
                return "redirect:/register";
            }

        } catch (RuntimeException e) {
            return "redirect:/inscriptionEchec";
        }
    }

    public void sendVerificationEmail(String email, String token, HttpServletRequest request) {
        String subject = "Activation du compte PictoPicto";
        String verificationLink = Utility.getSiteURL(request) + "/verify/" + token;

        // On crée un contexte Thymeleaf et on ajoute les variables nécessaires
        Context context = new Context();
        context.setVariable("verificationLink", verificationLink);

        // On traite le template Thymeleaf avec le contexte
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


