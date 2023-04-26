package com.ecam.picto.pictopro.repository;

import com.ecam.picto.pictopro.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag,Integer> {
    List<Tag> findAllByNomIn(List<String> listTags);
}
