package com.ecam.picto.pictopro.security.services;

public interface SecurityService {
    boolean isAuthenticated();
    void autoLogin(String username, String password);
}
