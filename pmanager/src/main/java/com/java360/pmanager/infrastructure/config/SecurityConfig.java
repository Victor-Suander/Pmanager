package com.java360.pmanager.infrastructure.config;

import com.java360.pmanager.infrastructure.security.AuthenticationFilter;
import com.java360.pmanager.infrastructure.security.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class SecurityConfig {

    private final AuthenticationService authenticationService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return   httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(c ->
                        c.sessionCreationPolicy(STATELESS)
                )
                .authorizeHttpRequests(r ->
                        r.requestMatchers("/**").authenticated()
                        )
                .addFilterBefore(
                        new AuthenticationFilter(authenticationService),
                        UsernamePasswordAuthenticationFilter.class
                )
                .build();
    }
}
