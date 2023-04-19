package com.ecam.picto.pictopro.repository;

import com.ecam.picto.pictopro.entity.Mot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotRepository extends JpaRepository<Mot,Integer> {
}
