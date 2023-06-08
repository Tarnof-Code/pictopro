package com.ecam.picto.pictopro.security;

import com.ecam.picto.pictopro.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
//        @EnableGlobalMethodSecurity
//(
        //securedEnabled = true,
        // jsr250Enabled = true,
//        prePostEnabled = true)
public class WebSecurityConfig {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //@Autowired
    // private AuthEntryPointJwt unauthorizedHandler;
//    @Autowired
//    private MyAccessDeniedHandler accessDeniedHandler;

//    @Bean
//    AuthTokenFilter authenticationJwtTokenFilter() {
//        return new AuthTokenFilter();
//    }

//    @Bean
//    DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//
//        authProvider.setUserDetailsService(userDetailsService);
//        authProvider.setPasswordEncoder(passwordEncoder());
//
//        return authProvider;
//    }

//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
//                .requestMatchers("/login", "/register", "/registration", "/inscriptionSucces", "/inscriptionEchec", "/images/**", "/css/**", "/js/**", "/api/auth/login", "/api/auth/signup").permitAll().requestMatchers("/dashboard/admin").hasRole("ADMIN").anyRequest()
//                .authenticated().and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .successHandler(new SavedRequestAwareAuthenticationSuccessHandler())
//                .defaultSuccessUrl("/dashboard/admin", true).failureUrl("/public/authFailed")
//                .and()
//                .logout().logoutSuccessUrl("/logout")
//                .permitAll()
//                .and()
//                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
//
//        http.authenticationProvider(authenticationProvider());
//
//        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers( "/login", "/register", "/registration", "/inscriptionSucces", "/inscriptionEchec", "/images/**", "/css/**", "/js/**", "/api/auth/login", "/api/auth/signup").permitAll().requestMatchers("/dashboard/admin").hasRole("ADMIN").anyRequest()
                .authenticated().and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();

        return http.build();
    }
//    @Bean
//    AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
//        return authConfig.getAuthenticationManager();
//    }

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
