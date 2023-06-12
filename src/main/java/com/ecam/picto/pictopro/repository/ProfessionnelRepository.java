package com.ecam.picto.pictopro.repository;

import com.ecam.picto.pictopro.entity.Professionnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionnelRepository extends JpaRepository<Professionnel, Integer> {
	Professionnel findByUsername(String username);

	Professionnel findById(int id);

	void deleteByUsername(String username);
}
