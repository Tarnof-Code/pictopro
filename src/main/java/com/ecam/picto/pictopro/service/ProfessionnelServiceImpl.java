package com.ecam.picto.pictopro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecam.picto.pictopro.entity.Professionnel;
import com.ecam.picto.pictopro.repository.ProfessionnelRepository;

@Service
public class ProfessionnelServiceImpl implements ProfessionnelService {

	@Autowired
	ProfessionnelRepository professionnelRepository;

	@Override
	public void ajouterUnPro(Professionnel professionnel) {
		professionnelRepository.save(professionnel);
	}

	@Override
	public List<Professionnel> findAll() {
		try {
			return professionnelRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Professionnel findById(Long id) {
		try {
			Optional<Professionnel> professionnel = professionnelRepository.findById(id);
			return professionnel.orElse(null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}