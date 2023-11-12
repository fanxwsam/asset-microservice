package com.asset.obs.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http
//               // .authorizeRequests()
//            .authorizeHttpRequests()
//            .requestMatchers("/actuator/**").permitAll()
//
//
////                .antMatchers("/customers/v3/**").permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .oauth2ResourceServer()
//                .jwt();
//
//        return http.build();

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf(CsrfConfigurer::disable)
            .authorizeHttpRequests(requests -> requests
                .requestMatchers("/swagger-ui/**", "/actuator/**", "/admin").permitAll()
                .anyRequest().authenticated())
            .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
            .build();
    }
}
