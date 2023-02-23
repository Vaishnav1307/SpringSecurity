package com.Security.SpringSecurityLesson2.service;

import com.Security.SpringSecurityLesson2.repository.UserRepository;
import com.Security.SpringSecurityLesson2.security.SecurityUser;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class JpaUserDetailedService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var u = userRepository.findUserByUserName(username);
        return u.map(SecurityUser::new).orElseThrow(()-> new UsernameNotFoundException("User not found in database with userName "+ username));
    }
}
