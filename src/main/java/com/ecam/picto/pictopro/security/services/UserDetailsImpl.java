//package com.ecam.picto.pictopro.security.services;
//
//import java.util.Collection;
//import java.util.Date;
//import java.util.List;
//import java.util.Objects;
//import java.util.stream.Collectors;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import com.ecam.picto.pictopro.entity.Professionnel;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//public class UserDetailsImpl implements UserDetails {
//    private static final long serialVersionUID = 1L;
//    private Long id;
//    private String username;
//    private String nom;
//    private String prenom;
//    private Date dateNaissance;
//    private String service;
//    private String email;
//    private String telephone;
//    @JsonIgnore
//    private String password;
//
//    private Collection<? extends GrantedAuthority> authorities;
//
//    public UserDetailsImpl(Long id, String username, String nom, String prenom, Date dateNaissance, String service, String email, String telephone, String password,
//                           Collection<? extends GrantedAuthority> authorities) {
//        this.id = id;
//        this.username = username;
//        this.nom = nom;
//        this.prenom = prenom;
//        this.dateNaissance = dateNaissance;
//        this.service = service;
//        this.email = email;
//        this.telephone = telephone;
//        this.password = password;
//        this.authorities = authorities;
//    }
//
//    public static UserDetailsImpl build(Professionnel user) {
//        List<GrantedAuthority> authorities = user.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
//
//        return new UserDetailsImpl(user.getId(), user.getUsername(), user.getNom(), user.getPrenom(), user.getDateNaissance(), user.getService(), user.getEmail(), user.getTelephone(), user.getPassword(), authorities);
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return authorities;
//    }
//
//    public Long getId() {
//        return id;
//    }
//    public String getEmail() {
//        return email;
//    }
//    @Override
//    public String getPassword() {
//        return password;
//    }
//    @Override
//    public String getUsername() {
//        return username;
//    }
//    public String getNom() {
//        return nom;
//    }
//    public String getPrenom() {
//        return prenom;
//    }
//    public Date getDateNaissance() {
//        return dateNaissance;
//    }
//    public String getService() {
//        return service;
//    }
//    public String getTelephone() {
//        return telephone;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o)
//            return true;
//        if (o == null || getClass() != o.getClass())
//            return false;
//        UserDetailsImpl user = (UserDetailsImpl) o;
//        return Objects.equals(id, user.id);
//    }
//}