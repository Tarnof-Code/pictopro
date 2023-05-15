package com.ecam.picto.pictopro.service;

import com.ecam.picto.pictopro.entity.Conjugaison;
import com.ecam.picto.pictopro.entity.Mot;
import com.ecam.picto.pictopro.repository.ConjugaisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConjugaisonServiceImpl implements ConjugaisonService{

    @Autowired
    ConjugaisonRepository conjugaisonRepository;

    @Override
    public List<Conjugaison> findConjugaisonByMot(Mot mot) {

        List<Conjugaison> listConjugaisons = mot.getIrregulier().getConjugaisons();

        return listConjugaisons;
    }
}
