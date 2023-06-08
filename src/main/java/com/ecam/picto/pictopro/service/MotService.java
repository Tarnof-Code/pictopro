package com.ecam.picto.pictopro.service;

import com.ecam.picto.pictopro.entity.Mot;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MotService {
    void ajouterUnMot(Mot mot);
    List<Mot> findAll();
    Mot findById(int id);
    Mot modifierUnMot(Mot ancienMot, Mot nouveauMot);

}
