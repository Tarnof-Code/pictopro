package com.ecam.picto.pictopro.security;

import com.ecam.picto.pictopro.entity.Professionnel;
import com.ecam.picto.pictopro.service.ProfessionnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator {
    @Autowired
    private ProfessionnelService professionnelService;
    Pattern usernamePattern = Pattern.compile("^[a-zA-ZÀ-ÿ0-9]+(([',.-][a-zA-ZÀ-ÿ0-9])?[a-zA-ZÀ-ÿ0-9])$");
    Pattern passwordPattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$");
    Pattern telephonePattern = Pattern.compile("^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$");
    Pattern generalPattern = Pattern.compile("^[a-zA-ZÀ-ÿ]+(([',. -][a-zA-ZÀ-ÿ ])?[a-zA-ZÀ-ÿ]*)*$");
    Pattern servicePattern = Pattern.compile("^[a-zA-ZÀ-ÿ0-9]+(([',. -][a-zA-ZÀ-ÿ0-9 ])?[a-zA-ZÀ-ÿ0-9]*)*$");
    Pattern emailPattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");

    @Override
    public boolean supports(Class<?> aClass) {
        return Professionnel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Professionnel user = (Professionnel) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        } else if (!usernamePattern.matcher(user.getUsername()).matches()) {
            errors.rejectValue("username", "Invalid.userForm.username");
        }
        if (professionnelService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username"); // Vérifie si le pseudo n'existe pas déjà
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        } else if (!passwordPattern.matcher(user.getPassword()).matches()) {
            errors.rejectValue("password", "Invalid.userForm.password");
        }

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "Diff.userForm.passwordConfirm");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nom", "NotEmpty");
        if (!generalPattern.matcher(user.getNom()).matches()) {
            errors.rejectValue("nom", "Invalid.userForm.nom");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "prenom", "NotEmpty");
        if (!generalPattern.matcher(user.getPrenom()).matches()) {
            errors.rejectValue("prenom", "Invalid.userForm.prenom");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateNaissance", "NotEmpty");
        if (user.getDateNaissance() == null) {
            errors.rejectValue("dateNaissance", "Invalid.userForm.dateNaissance");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "service", "NotEmpty");
        if (!servicePattern.matcher(user.getService()).matches()) {
            errors.rejectValue("service", "Invalid.userForm.service");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
        if (!emailPattern.matcher(user.getEmail()).matches()) {
            errors.rejectValue("email", "Invalid.userForm.email");
        }
        if (professionnelService.findByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "Duplicate.userForm.email"); // Vérifie si l'email n'existe pas déjà
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telephone", "NotEmpty");
        if (!telephonePattern.matcher(user.getTelephone()).matches()) {
            errors.rejectValue("telephone", "Invalid.userForm.telephone");
        }
    }
}