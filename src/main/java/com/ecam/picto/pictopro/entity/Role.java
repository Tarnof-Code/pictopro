package com.ecam.picto.pictopro.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //@Enumerated(EnumType.STRING)
    @Column(length = 20)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<Professionnel> professionnels;

    public Role() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Professionnel> getProfessionnels() {
        return professionnels;
    }

    public void setProfessionnels(Set<Professionnel> professionnels) {
        this.professionnels = professionnels;
    }
}