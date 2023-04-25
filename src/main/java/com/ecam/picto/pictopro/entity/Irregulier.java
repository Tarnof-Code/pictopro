package com.ecam.picto.pictopro.entity;

import jakarta.persistence.*;

@Entity
public class Irregulier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String forme;
    @OneToOne
    private Mot mot;

}
