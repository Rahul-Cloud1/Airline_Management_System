package com.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {
    @org.springframework.beans.factory.annotation.Value("${security.user.name:${spring.security.user.name:user}}")
    private String username;

    @org.springframework.beans.factory.annotation.Value("${security.user.password:${spring.security.user.password:password}}")
    private String rawPassword;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeExchange(exchanges -> exchanges.anyExchange().authenticated())
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.withUsername(username)
                .password(passwordEncoder.encode(rawPassword))
                .roles("USER")
                .build();
        return new MapReactiveUserDetailsService(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
