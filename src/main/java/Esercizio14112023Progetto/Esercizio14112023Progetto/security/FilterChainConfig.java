package Esercizio14112023Progetto.Esercizio14112023Progetto.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class FilterChainConfig {
    @Autowired
    private CustomFilter customFilter;
    @Autowired
    private FilterExceptions filterExceptions;
    @Bean
    SecurityFilterChain securityFilterChainConfig(HttpSecurity http) throws Exception {
        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());
        http.formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.disable());

        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(filterExceptions,CustomFilter.class);
        http.authorizeHttpRequests(request->request.requestMatchers("/**").permitAll());

        return http.build();
    }
}
