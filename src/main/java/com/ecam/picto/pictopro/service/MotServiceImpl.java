package com.ecam.picto.pictopro.service;

import com.ecam.picto.pictopro.entity.Irregulier;
import com.ecam.picto.pictopro.entity.Mot;
import com.ecam.picto.pictopro.repository.MotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotServiceImpl implements MotService{

    @Autowired
    MotRepository motRepository;

    @Override
    public void ajouterUnMot(Mot mot) {
        motRepository.save(mot);
    }

    @Override
    public List<Mot> findAll() {
        try{
            return motRepository.findAll();
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Mot findById(int id) {
        try{
            Optional<Mot> mot = motRepository.findById(id);
            return mot.orElse(null);
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Mot modifierUnMot(Mot ancienMot, Mot nouveauMot) {
        try{
            Mot motAModifier = findById(ancienMot.getId());
            System.out.println("Mot à modifier "+ motAModifier);

            motAModifier.setNom(nouveauMot.getNom());
            motAModifier.setPictoFile(nouveauMot.getPictoFile());
            motAModifier.setCategorie(nouveauMot.getCategorie());
            motAModifier.setTags(nouveauMot.getTags());

            if(nouveauMot.getSousCategorie() != null) {
                motAModifier.setSousCategorie(nouveauMot.getSousCategorie());
            }
            if(nouveauMot.getIrregulier() != null){
                motAModifier.setIrregulier(nouveauMot.getIrregulier());
            }


            Mot motModifie = motRepository.save(motAModifier);

            return motModifie;

        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
