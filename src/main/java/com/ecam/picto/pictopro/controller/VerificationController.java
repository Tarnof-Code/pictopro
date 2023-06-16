package com.ecam.picto.pictopro.controller;

import com.ecam.picto.pictopro.entity.Professionnel;
import com.ecam.picto.pictopro.repository.ProfessionnelRepository;
import com.ecam.picto.pictopro.security.services.SecurityService;
import com.ecam.picto.pictopro.service.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/verify")
public class VerificationController {
    private final ProfessionnelRepository professionnelRepository;
    private final VerificationTokenService verificationTokenService;
    private final SecurityService securityService;

    @Autowired
    public VerificationController(ProfessionnelRepository professionnelRepository,
                                  VerificationTokenService verificationTokenService,
                                  SecurityService securityService) {
        this.professionnelRepository = professionnelRepository;
        this.verificationTokenService = verificationTokenService;
        this.securityService = securityService;
    }

    @GetMapping("/{token}")
    public String verifyAccount(@PathVariable("token") String token) {
        // Retrieve the user associated with the verification token
        Professionnel professionnel = verificationTokenService.getUserByVerificationToken(token);
        if (professionnel == null) {
            return "verificationError";
        }
        // Update the user's account status to indicate it is verified
        professionnel.setActive(true);
        professionnel.setVerificationToken(null);

        professionnelRepository.save(professionnel);
        return "verificationSuccess";
    }
}


