package com.ecam.picto.pictopro.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //@Enumerated(EnumType.STRING)
    @Column(length = 20)
    private String name;

    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER)
    private List<Professionnel> listeProfessionnelsParRole;

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

    public List<Professionnel> getListeProfessionnelsParRole() {
        return listeProfessionnelsParRole;
    }

    public void setListeProfessionnelsParRole(List<Professionnel> listeProfessionnelsParRole) {
        this.listeProfessionnelsParRole = listeProfessionnelsParRole;
    }
}