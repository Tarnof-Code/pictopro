package com.ecam.picto.pictopro.security.services;

import com.ecam.picto.pictopro.entity.Professionnel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;
    private int id;
    private String username;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String service;
    private String email;
    private String telephone;
    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authority;

    public UserDetailsImpl(int id, String username, String nom, String prenom, Date dateNaissance, String service, String email, String telephone, String password,
                           Collection<? extends GrantedAuthority> authority) {
        this.id = id;
        this.username = username;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.service = service;
        this.email = email;
        this.telephone = telephone;
        this.password = password;
        this.authority = authority;
    }

    public static UserDetailsImpl build(Professionnel user) {
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getName());

        return new UserDetailsImpl(user.getId(), user.getUsername(), user.getNom(), user.getPrenom(), user.getDateNaissance(), user.getService(), user.getEmail(), user.getTelephone(), user.getPassword(), Collections.singletonList(authority));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authority;
    }

    public int getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return username;
    }
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public Date getDateNaissance() {
        return dateNaissance;
    }
    public String getService() {
        return service;
    }
    public String getTelephone() {
        return telephone;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}