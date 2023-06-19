package com.ecam.picto.pictopro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecam.picto.pictopro.entity.DossierMedical;

@Service
public interface DossierMedicalService {
	void ajouterUnDossierMedical(DossierMedical dossierMedical);

	List<DossierMedical> findAll();

	DossierMedical findById(int id);

	List<DossierMedical> findByForeignKey(int id);
}