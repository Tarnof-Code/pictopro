package com.ecam.picto.pictopro.security;


import org.apache.tomcat.websocket.AuthenticationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class MyAuthenticationManager implements AuthenticationManager {

    @Override
    public Authentication authenticate(Authentication authentication) {
        // Put here your authentication logic
        // You can access the username via the authentication object
        // If the authentication is successful
        // then return a valid authentication object
        // e.g. using the UsernamePasswordAuthenticationToken class
        return null;
    }

}
