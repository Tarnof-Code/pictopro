package com.ecam.picto.pictopro.service;

import com.ecam.picto.pictopro.entity.Categorie;
import com.ecam.picto.pictopro.entity.Tag;
import com.ecam.picto.pictopro.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService{

    @Autowired
    TagRepository tagRepository;

    @Override
    public List<Tag> afficherTags() {
        try{
            return tagRepository.findAll();
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Tag> findAllByNomIn(List<String> listTags) {
        try{
            return tagRepository.findAllByNomIn(listTags);
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
