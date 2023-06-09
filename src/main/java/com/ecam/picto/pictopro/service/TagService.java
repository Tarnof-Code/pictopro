package com.ecam.picto.pictopro.service;

import com.ecam.picto.pictopro.entity.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TagService {
    List<Tag> afficherTags();
    List<Tag> findAllByNomIn(List<String> listTags);
}
