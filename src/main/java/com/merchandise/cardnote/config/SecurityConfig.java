package com.merchandise.cardnote.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.merchandise.cardnote.service.UserDetailsServiceImpl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;

    private final AuthenticationFailureHandler customFailureHandler;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain springSecurity(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> {
                    csrf.disable();
                })
                .authorizeHttpRequests(auth -> {
                    auth
                            .requestMatchers("/", "/user/**", "/layouts/**", "/css/**", "/img/**",
                                    "/js/**")
                            .permitAll()
                            .anyRequest().authenticated();

                })
                .formLogin(login -> {
                    login
                            .loginPage("/user/login").defaultSuccessUrl("/", true)
                            .loginProcessingUrl("/user/login").defaultSuccessUrl("/", true)
                            .failureHandler(customFailureHandler);
                })
                .logout(logout -> {
                    logout.logoutUrl("/logout").logoutSuccessUrl("/");
                })
                .userDetailsService(userDetailsService);

        return http.build();
    }

}
