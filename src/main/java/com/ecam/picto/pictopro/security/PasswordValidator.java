package com.ecam.picto.pictopro.security;

import com.ecam.picto.pictopro.entity.Professionnel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class PasswordValidator implements Validator {

    Pattern passwordPattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$");

    @Override
    public boolean supports(Class<?> aClass) {
        return Professionnel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Professionnel user = (Professionnel) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        } else if (!passwordPattern.matcher(user.getPassword()).matches()) {
            errors.rejectValue("password", "Invalid.userForm.password");
        }

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "Diff.userForm.passwordConfirm");
        }
    }
}
