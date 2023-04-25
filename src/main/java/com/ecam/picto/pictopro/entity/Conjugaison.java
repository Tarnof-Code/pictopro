package com.ecam.picto.pictopro.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Conjugaison {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String temps;
    private String premierePersSing;
    private String deuxiemePersSing;
    private String troisiemePersSing;
    private String premierePersPluriel;
    private String deuxiemePersPluriel;
    private String troisiemePersPluriel;


}
