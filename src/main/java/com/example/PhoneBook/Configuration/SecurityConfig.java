package com.example.PhoneBook.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)
    {
        try
        {
            http.authorizeHttpRequests(auth->{
                auth.requestMatchers("/*").permitAll();
                auth.requestMatchers("/assets/**").permitAll();
                auth.requestMatchers("/user/**").hasAuthority("User");
                // auth.requestMatchers("/user/**").permitAll();
            });
            http.formLogin().permitAll();
            http.csrf().disable();
            return http.build();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
