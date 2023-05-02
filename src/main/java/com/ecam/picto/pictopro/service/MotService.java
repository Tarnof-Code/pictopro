package com.ecam.picto.pictopro.service;

import com.ecam.picto.pictopro.entity.Categorie;
import com.ecam.picto.pictopro.entity.Mot;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MotService {
    public void ajouterUnMot(Mot mot);
    public List<Mot> findAll();
    public Mot findById(int id);

}
