package com.ecam.picto.pictopro.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Irregulier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String participePasse;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Conjugaison> conjugaisons;
    private String pluriel;
    private String feminin;
    @OneToOne
    private Mot mot;

}
