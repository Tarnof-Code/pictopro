package com.ecam.picto.pictopro.security.services;

import com.ecam.picto.pictopro.entity.Professionnel;
import com.ecam.picto.pictopro.repository.ProfessionnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private ProfessionnelRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Professionnel user = userRepository.findByUsername(username);
        if (user == null || !user.isActive()) {
            throw new UsernameNotFoundException("Compte introuvable ou non activé: " + username);
        }

        return UserDetailsImpl.build(user);
    }
}



