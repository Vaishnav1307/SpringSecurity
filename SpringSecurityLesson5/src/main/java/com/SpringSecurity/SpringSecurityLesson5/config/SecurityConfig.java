package com.SpringSecurity.SpringSecurityLesson5.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .httpBasic()
                .and()
                .authorizeRequests()
//                .anyRequest().permitAll()
//                .anyRequest().authenticated() // end point level authorization
//                .anyRequest().denyAll()
//                .anyRequest().hasAuthority("read")
//                .anyRequest().hasAnyAuthority("read","write")
//                .anyRequest().hasRole("ADMIN")
//                .anyRequest().hasAnyRole("ADMIN","MANAGER")
                .anyRequest().access("isAuthenticated() and hasAuthority('read')") // SpEL --> authorization rules
                .and()
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        var uds = new InMemoryUserDetailsManager();

        var u1 = User.withUsername("Vaishnav")
                .password(passwordEncoder().encode("VAISHNAV"))
//                .roles("ADMIN")  // equivalent with authority ROLE_ADMIN
                .authorities("read")
                .build();

        var u2 = User.withUsername("Chotya")
                .password(passwordEncoder().encode("CHOTYA"))
//                .authorities("ROLE_MANAGER")
                .authorities("write")
                .build();

        uds.createUser(u1);
        uds.createUser(u2);
        return uds;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
