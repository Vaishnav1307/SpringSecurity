package com.SpringSecurity.SpringSecurityLesson7.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.httpBasic().and().authorizeHttpRequests().anyRequest().authenticated().and().build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var u1 = User.withUsername("vaishnav").password(passwordEncoder().encode("VAISHNAV")).authorities("read").build();

        var u2 = User.withUsername("chotya").password(passwordEncoder().encode("CHOTYA")).authorities("write").build();

        var uds = new InMemoryUserDetailsManager();

        uds.createUser(u1);
        uds.createUser(u2);

        return uds;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
