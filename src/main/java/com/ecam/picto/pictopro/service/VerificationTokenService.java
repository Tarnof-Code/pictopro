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
    public VerificationTokenService(ProfessionnelRepository professionnelRepository) {
        this.professionnelRepository = professionnelRepository;
    }

    public String generateToken() {
        // On génère un unique token de vérification avec UUID (universally Unique Identifier)
        String token = UUID.randomUUID().toString();
        return token;
    }

    public void saveVerificationToken(Professionnel userForm, String token) {
        // On associe le token de vérification avec le compte créé
        userForm.setVerificationToken(token);
    }

    public Professionnel getUserByVerificationToken(String token) {
        // On récupère l'utilisateur associé au token de vérification dans la base de donnée
        return professionnelRepository.findByVerificationToken(token);
    }
}

