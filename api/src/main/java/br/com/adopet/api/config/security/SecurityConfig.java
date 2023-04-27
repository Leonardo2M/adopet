package br.com.adopet.api.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final SecurityFilter securityFilter;

    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/abrigos").permitAll()
                .requestMatchers(HttpMethod.POST, "/tutores").permitAll()
                .requestMatchers(HttpMethod.PUT, "/tutores/**").hasRole("TUTOR")
                .requestMatchers(HttpMethod.DELETE, "/tutores/**").hasRole("TUTOR")
                .requestMatchers(HttpMethod.PUT, "/abrigos/**").hasRole("ABRIGO")
                .requestMatchers(HttpMethod.DELETE, "/abrigos/**").hasRole("ABRIGO")
                .requestMatchers(HttpMethod.POST, "/pets").hasRole("ABRIGO")
                .requestMatchers(HttpMethod.PUT, "/pets/**").hasRole("ABRIGO")
                .requestMatchers(HttpMethod.DELETE, "/pets/**").hasRole("ABRIGO")
                .requestMatchers(HttpMethod.POST, "/adocao").hasRole("ABRIGO")
                .requestMatchers(HttpMethod.DELETE, "/adocao/**").hasRole("ABRIGO")
                .anyRequest().authenticated()
                .and().addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return  new BCryptPasswordEncoder();
    }

}
