package com.ecam.picto.pictopro.controller;

import com.ecam.picto.pictopro.entity.Professionnel;
import com.ecam.picto.pictopro.security.PasswordValidator;
import com.ecam.picto.pictopro.exception.ProfessionnelNotFoundException;
import com.ecam.picto.pictopro.service.ProfessionnelService;
import com.ecam.picto.pictopro.utility.Utility;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

@Controller
public class ForgotPasswordController {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private ProfessionnelService professionnelService;
    @Autowired
    private PasswordValidator passwordValidator;

    @GetMapping("/forgot_password")
    public String showForgotPasswordForm() {
        return "forgot_password_form";
    }

    @PostMapping("/forgot_password")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        String token = RandomString.make(30);
        Date expirationTime = calculateExpirationTime(); // Calculate the expiration time 24 hours from the current time

        try {
            professionnelService.updateResetPasswordTokenWithExpiration(token, email, expirationTime);
            String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
            sendEmail(email, resetPasswordLink);
            model.addAttribute("message", "Le lien de réinitialisation du mot de passe vous a été envoyé à votre adresse email");

        } catch (ProfessionnelNotFoundException ex) {
            model.addAttribute("error", ex.getMessage());
        } catch (UnsupportedEncodingException | MessagingException e) {
            model.addAttribute("error", "Erreur durant l'envoi de l'email");
        }
        return "forgot_password_form";
    }

    private Date calculateExpirationTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, 24); // Add 24 hours to the current time
        return calendar.getTime();
    }

    public void sendEmail(String recipientEmail, String link)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("contact@pictopicto.fr", "Picto Support");
        helper.setTo(recipientEmail);

        String subject = "Voici le lien pour la réinitialisation du mot de passe";

        String content = "<p>Bonjour,</p>"
                + "<p>Vous avez demandé la réinitialisation de votre mot de passe.</p>"
                + "<p>Cliquez sur le lien qui est valide durant 24 heures pour changer de mot de passe :</p>"
                + "<p><a href=\"" + link + "\">Changer mon mot de passe</a></p>"
                + "<br>"
                + "<p>Ignorez cet email si vous vous souvenez de votre mot de passe, "
                + "ou si vous n'avez pas fait de demande de réinitialisation.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }

    @GetMapping("/reset_password")
    public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
        Professionnel user = professionnelService.getByResetPasswordToken(token);

        if (user == null) {
            model.addAttribute("message", "Token invalide");
            return "message";
        }

        model.addAttribute("token", token);
        model.addAttribute("userForm", user);
        model.addAttribute("title", "Réinitialiser votre mot de passe");

        return "reset_password_form";
    }

    @PostMapping("/reset_password")
    public String processResetPassword(@ModelAttribute("userForm") Professionnel userForm, HttpServletRequest request, Model model, BindingResult bindingResult) {
        String token = request.getParameter("token");
        String password = userForm.getPassword();

        Professionnel user = professionnelService.getByResetPasswordToken(token);
        model.addAttribute("title", "Réinitialiser votre mot de passe");

        if (user == null) {
            model.addAttribute("message", "Token invalide");
            return "message";
        } else {
            passwordValidator.validate(userForm, bindingResult);

            if (bindingResult.hasErrors()) {
                model.addAttribute("fields", bindingResult);
                model.addAttribute("token", token);
                return "reset_password_form";
            }

            professionnelService.updatePassword(user, password);
            model.addAttribute("message", "Votre mot de passe a été changé avec succès.");
        }

        return "message";
    }

    @GetMapping("/message")
    public String showMessage() {
        return "message";
    }
}
