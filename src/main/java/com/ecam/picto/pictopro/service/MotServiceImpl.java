package com.ecam.picto.pictopro.service;

import com.ecam.picto.pictopro.entity.Conjugaison;
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
            Irregulier irregulierMotAModifier = motAModifier.getIrregulier();
            Irregulier irregulierNouveauMot = nouveauMot.getIrregulier();

            motAModifier.setNom(nouveauMot.getNom());
            motAModifier.setPictoFile(nouveauMot.getPictoFile());
            motAModifier.setCategorie(nouveauMot.getCategorie());
            motAModifier.setTags(nouveauMot.getTags());

            if(nouveauMot.getSousCategorie() != null) {
                motAModifier.setSousCategorie(nouveauMot.getSousCategorie());
            }

            if(irregulierMotAModifier != null && irregulierNouveauMot != null){

                irregulierMotAModifier.setPluriel(irregulierNouveauMot.getPluriel());
                irregulierMotAModifier.setFeminin(irregulierNouveauMot.getFeminin());
                irregulierMotAModifier.setParticipePasse(irregulierNouveauMot.getParticipePasse());

                List<Conjugaison> conjugaisonsMotAModifier = irregulierMotAModifier.getConjugaisons();
                List<Conjugaison> conjugaisonsNouveauMot = irregulierNouveauMot.getConjugaisons();

                if(!conjugaisonsMotAModifier.isEmpty() && !conjugaisonsNouveauMot.isEmpty()){

                    for (int i = 0; i < conjugaisonsMotAModifier.size(); i++) {

                        Conjugaison aModif = conjugaisonsMotAModifier.get(i);
                        Conjugaison nouvelle = conjugaisonsNouveauMot.get(i);

                        aModif.setPremierePersSing(nouvelle.getPremierePersSing());
                        aModif.setDeuxiemePersSing(nouvelle.getDeuxiemePersSing());
                        aModif.setTroisiemePersSing(nouvelle.getTroisiemePersSing());
                        aModif.setPremierePersPluriel(nouvelle.getPremierePersPluriel());
                        aModif.setDeuxiemePersPluriel(nouvelle.getDeuxiemePersPluriel());
                        aModif.setTroisiemePersPluriel(nouvelle.getTroisiemePersPluriel());
                        }
                    }

                }

           if(irregulierMotAModifier == null && irregulierNouveauMot != null){
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
