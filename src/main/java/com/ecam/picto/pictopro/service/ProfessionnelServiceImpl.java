package com.ecam.picto.pictopro.service;

import java.util.*;


import com.ecam.picto.pictopro.entity.Role;
import com.ecam.picto.pictopro.exception.ProfessionnelNotFoundException;
import com.ecam.picto.pictopro.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecam.picto.pictopro.entity.Professionnel;
import com.ecam.picto.pictopro.repository.ProfessionnelRepository;

@Service
@Transactional
public class ProfessionnelServiceImpl implements ProfessionnelService {

    @Autowired
    ProfessionnelRepository professionnelRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void savePro(Professionnel user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Optional<Role> defaultRoleOptional = roleRepository.findByName("ROLE_PRO");
        if (defaultRoleOptional.isPresent()) {
            Role defaultRole = defaultRoleOptional.get();
            user.setRole((defaultRole));
        }
        professionnelRepository.save(user);
    }

    @Override
    public void updatePro(Professionnel user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Optional<Role> defaultRoleOptional = roleRepository.findByName("ROLE_PRO");
        if (defaultRoleOptional.isPresent()) {
            Role defaultRole = defaultRoleOptional.get();
            user.setRole((defaultRole));
        }
        user.setActive(true);
        professionnelRepository.save(user);
    }

    public void updateAdmin(Professionnel user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Optional<Role> defaultRoleOptional = roleRepository.findByName("ROLE_ADMIN");
        if (defaultRoleOptional.isPresent()) {
            Role defaultRole = defaultRoleOptional.get();
            user.setRole((defaultRole));
        }
        user.setActive(true);
        professionnelRepository.save(user);
    }

    @Override
    public List<Professionnel> findAll() {
        try {
            return professionnelRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Professionnel findById(int id) {
        return professionnelRepository.findById(id);
    }

    @Override
    public Professionnel findByUsername(String username) {
        return professionnelRepository.findByUsername(username);
    }

    @Override
    public Professionnel findByEmail(String email) {
        return professionnelRepository.findByEmail(email);
    }

    public void deleteCompteById(int id) {
        professionnelRepository.deleteById(id);
    }

    @Override
    public void updateResetPasswordTokenWithExpiration(String token, String email, Date expirationTime) throws ProfessionnelNotFoundException {
        Professionnel user = professionnelRepository.findByEmail(email);
        if (user != null) {
            user.setResetPasswordToken(token);
            user.setResetPasswordTokenExpiration(expirationTime); // Set the token's expiration time
            professionnelRepository.save(user);
        } else {
            throw new ProfessionnelNotFoundException("Aucun utilisateur ne correspond à cet email : " + email);
        }
    }

    public Professionnel getByResetPasswordToken(String token) {
        return professionnelRepository.findByResetPasswordToken(token);
    }

    public void updatePassword(Professionnel user, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        user.setResetPasswordToken(null);
        professionnelRepository.save(user);
    }

    @Override
    public Professionnel findByVerificationToken(String token) {
        return professionnelRepository.findByVerificationToken(token);
    }

}