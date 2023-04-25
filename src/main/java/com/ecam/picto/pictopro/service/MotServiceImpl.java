package com.ecam.picto.pictopro.service;

import com.ecam.picto.pictopro.entity.Mot;
import com.ecam.picto.pictopro.repository.MotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MotServiceImpl implements MotService{

    @Autowired
    MotRepository motRepository;

    @Override
    public void ajouterUnMot(Mot mot) {
        motRepository.save(mot);
    }
}
