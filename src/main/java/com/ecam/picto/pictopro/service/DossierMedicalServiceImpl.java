package com.ecam.picto.pictopro.service;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecam.picto.pictopro.entity.DossierMedical;
import com.ecam.picto.pictopro.repository.DossierMedicalRepository;

@Service
public class DossierMedicalServiceImpl implements DossierMedicalService {

	@Autowired
	DossierMedicalRepository dossierMedicalRepository;

	@Override
	public void ajouterUnDossierMedical(@Valid DossierMedical dossierMedical) {
		dossierMedicalRepository.save(dossierMedical);
	}

	@Override
	public List<DossierMedical> findAll() {
		try {
			return dossierMedicalRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<DossierMedical> findByForeignKey(int id) {
		try {
			return (List<DossierMedical>) dossierMedicalRepository.findAllByProfessionnelId(id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public DossierMedical findById(int id) {
		try {
			Optional<DossierMedical> dossierMedical = dossierMedicalRepository.findById(id);
			return dossierMedical.orElse(null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
