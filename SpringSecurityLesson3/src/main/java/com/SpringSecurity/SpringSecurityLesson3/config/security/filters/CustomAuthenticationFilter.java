package com.SpringSecurity.SpringSecurityLesson3.config.security.filters;

import com.SpringSecurity.SpringSecurityLesson3.config.security.authentication.CustomAuthentication;
import com.SpringSecurity.SpringSecurityLesson3.config.security.managers.CustomeAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    private final CustomeAuthenticationManager customeAuthenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request
            , HttpServletResponse response
            , FilterChain filterChain) throws ServletException, IOException {

        // 1. create an authentication object which is not yet authenticated
        String key = String.valueOf(request.getHeader("key"));
        CustomAuthentication ca = new CustomAuthentication(false, key);

        // 2. delegate authentication object to manager
        // 3. get back authentication from manager
        // 4. if object is authenticated then send request to next filter in chain
        var a = customeAuthenticationManager.authenticate(ca);

        if (a.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(a);
            filterChain.doFilter(request, response); // only when authentication worked
        }
    }
}
