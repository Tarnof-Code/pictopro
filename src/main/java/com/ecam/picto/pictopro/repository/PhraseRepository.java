package com.ecam.picto.pictopro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecam.picto.pictopro.entity.Phrase;

@Repository
public interface PhraseRepository extends JpaRepository<Phrase, Integer> {
	Iterable<Phrase> findAllByDossierMedicalId(int id);
}