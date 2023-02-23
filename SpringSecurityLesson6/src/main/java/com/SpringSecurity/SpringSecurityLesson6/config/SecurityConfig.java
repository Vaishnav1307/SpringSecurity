package com.SpringSecurity.SpringSecurityLesson6.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    @Bean
    public UserDetailsService userDetailsService() {
        var uds = new InMemoryUserDetailsManager();

        var u1 = User.withUsername("vaishnav").password(passwordEncoder().encode("VAISHNAV")).authorities("read").build();

        var u2 = User.withUsername("chotya").password(passwordEncoder().encode("CHOTYA")).authorities("write").build();

        uds.createUser(u1);
        uds.createUser(u2);

        return uds;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic().and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/demo/**").hasAuthority("read")
                .anyRequest().authenticated()
                .and().build();
    }
}
