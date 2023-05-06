package com.ecam.picto.pictopro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecam.picto.pictopro.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
}