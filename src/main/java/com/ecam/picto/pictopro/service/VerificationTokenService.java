package com.ecam.picto.pictopro.service;

import com.ecam.picto.pictopro.entity.Professionnel;
import com.ecam.picto.pictopro.repository.ProfessionnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VerificationTokenService {

    private final ProfessionnelRepository professionnelRepository;

    @Autowired
    private ProfessionnelService professionnelService;

    @Autowired
    public VerificationTokenService(ProfessionnelRepository professionnelRepository) {
        this.professionnelRepository = professionnelRepository;
    }

    public String generateToken() {
        // Generate a unique verification token using UUID
        String token = UUID.randomUUID().toString();
        return token;
    }

    public void saveVerificationToken(Professionnel userForm, String token) {
        // Associate the token with the user's account for future verification
        userForm.setVerificationToken(token);

    }

    public Professionnel getUserByVerificationToken(String token) {
        // Retrieve the user associated with the verification token from the database
        return professionnelRepository.findByVerificationToken(token);
    }
}

