package com.ecam.picto.pictopro.entity;

import jakarta.persistence.*;

@Entity
public class Conjugaison {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Irregulier irregulier;
    private String temps;
    private String premierePersSing;
    private String deuxiemePersSing;
    private String troisiemePersSing;
    private String premierePersPluriel;
    private String deuxiemePersPluriel;
    private String troisiemePersPluriel;


}
