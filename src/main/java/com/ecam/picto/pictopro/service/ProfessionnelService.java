package com.ecam.picto.pictopro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecam.picto.pictopro.entity.Professionnel;

@Service
public interface ProfessionnelService {
	void save(Professionnel professionnel);

	List<Professionnel> findAll();

	Professionnel findById(int id);

	Professionnel findByUsername (String username);
}
