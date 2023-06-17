package com.ecam.picto.pictopro.repository;

import com.ecam.picto.pictopro.entity.Professionnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionnelRepository extends JpaRepository<Professionnel, Integer> {
	Professionnel findByUsername(String username);
	Professionnel findByEmail(String email);
	Professionnel findById(int id);
	Professionnel findByResetPasswordToken(String token);
	Professionnel findByVerificationToken(String token);
    Professionnel findByUsernameAndIdNot(String username, int userId);
	Professionnel findByEmailAndIdNot(String email, int userId);
}
