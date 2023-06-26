package com.ecam.picto.pictopro.repository;

import com.ecam.picto.pictopro.entity.Categorie;
import com.ecam.picto.pictopro.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategorieRepository extends JpaRepository<Categorie,Integer> {
//    Iterable<Categorie> findAllByQuestionId(int id);

//    List<Categorie> findAllByQuestion(List<Questions> questions);

}
