package com.ecam.picto.pictopro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecam.picto.pictopro.entity.DossierMedical;

@Repository
public interface DossierMedicalRepository extends JpaRepository<DossierMedical, Integer> {
	Iterable<DossierMedical> findAllByProfessionnelId(int id);
}