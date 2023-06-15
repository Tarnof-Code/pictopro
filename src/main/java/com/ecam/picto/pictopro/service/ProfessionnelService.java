package com.ecam.picto.pictopro.service;

import com.ecam.picto.pictopro.entity.Professionnel;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface ProfessionnelService {

    void savePro(Professionnel professionnel);

    List<Professionnel> findAll();

    Professionnel findById(int id);

    Professionnel findByUsername(String username);

    void deleteCompteById(int id);

    void updateAdmin(Professionnel user);

    Professionnel findByEmail(String email);

    void updateResetPasswordTokenWithExpiration(String token, String email, Date expirationTime) throws ProfessionnelNotFoundException;

    Professionnel getByResetPasswordToken(String token);

    void updatePassword(Professionnel user, String newPassword);

}
