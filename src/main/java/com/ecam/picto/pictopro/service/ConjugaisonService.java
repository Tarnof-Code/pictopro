package com.ecam.picto.pictopro.service;

import com.ecam.picto.pictopro.entity.Conjugaison;
import com.ecam.picto.pictopro.entity.Mot;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConjugaisonService {
    public List<Conjugaison> findConjugaisonByMot(Mot mot);
}
