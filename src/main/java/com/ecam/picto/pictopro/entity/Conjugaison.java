package com.ecam.picto.pictopro.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

@Entity
public class Conjugaison {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="irregulier_id",insertable = false,updatable = false)
    @JsonIgnore
    private Irregulier irregulier;

    private String temps;
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ]+(([',. -][a-zA-ZÀ-ÿ ])?[a-zA-ZÀ-ÿ]*)*$", message = "* Format de caractère non autorisé")
    private String premierePersSing;
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ]+(([',. -][a-zA-ZÀ-ÿ ])?[a-zA-ZÀ-ÿ]*)*$", message = "* Format de caractère non autorisé")
    private String deuxiemePersSing;
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ]+(([',. -][a-zA-ZÀ-ÿ ])?[a-zA-ZÀ-ÿ]*)*$", message = "* Format de caractère non autorisé")
    private String troisiemePersSing;
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ]+(([',. -][a-zA-ZÀ-ÿ ])?[a-zA-ZÀ-ÿ]*)*$", message = "* Format de caractère non autorisé")
    private String premierePersPluriel;
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ]+(([',. -][a-zA-ZÀ-ÿ ])?[a-zA-ZÀ-ÿ]*)*$", message = "* Format de caractère non autorisé")
    private String deuxiemePersPluriel;
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ]+(([',. -][a-zA-ZÀ-ÿ ])?[a-zA-ZÀ-ÿ]*)*$", message = "* Format de caractère non autorisé")
    private String troisiemePersPluriel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Irregulier getIrregulier() {
        return irregulier;
    }

    public void setIrregulier(Irregulier irregulier) {
        this.irregulier = irregulier;
    }

    public String getTemps() {
        return temps;
    }

    public void setTemps(String temps) {
        this.temps = temps;
    }

    public String getPremierePersSing() {
        return premierePersSing;
    }

    public void setPremierePersSing(String premierePersSing) {
        this.premierePersSing = premierePersSing;
    }

    public String getDeuxiemePersSing() {
        return deuxiemePersSing;
    }

    public void setDeuxiemePersSing(String deuxiemePersSing) {
        this.deuxiemePersSing = deuxiemePersSing;
    }

    public String getTroisiemePersSing() {
        return troisiemePersSing;
    }

    public void setTroisiemePersSing(String troisiemePersSing) {
        this.troisiemePersSing = troisiemePersSing;
    }

    public String getPremierePersPluriel() {
        return premierePersPluriel;
    }

    public void setPremierePersPluriel(String premierePersPluriel) {
        this.premierePersPluriel = premierePersPluriel;
    }

    public String getDeuxiemePersPluriel() {
        return deuxiemePersPluriel;
    }

    public void setDeuxiemePersPluriel(String deuxiemePersPluriel) {
        this.deuxiemePersPluriel = deuxiemePersPluriel;
    }

    public String getTroisiemePersPluriel() {
        return troisiemePersPluriel;
    }

    public void setTroisiemePersPluriel(String troisiemePersPluriel) {
        this.troisiemePersPluriel = troisiemePersPluriel;
    }

    @Override
    public String toString() {
        return "Conjugaison{" +
                "id=" + id +
                ", temps='" + temps + '\'' +
                ", premierePersSing='" + premierePersSing + '\'' +
                ", deuxiemePersSing='" + deuxiemePersSing + '\'' +
                ", troisiemePersSing='" + troisiemePersSing + '\'' +
                ", premierePersPluriel='" + premierePersPluriel + '\'' +
                ", deuxiemePersPluriel='" + deuxiemePersPluriel + '\'' +
                ", troisiemePersPluriel='" + troisiemePersPluriel + '\'' +
                '}';
    }
}
