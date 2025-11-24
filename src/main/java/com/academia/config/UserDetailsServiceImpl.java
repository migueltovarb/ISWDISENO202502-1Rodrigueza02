package com.academia.config;

import com.academia.model.User;
import com.academia.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User foundUser = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        // Rol con prefijo ROLE_
        GrantedAuthority authority =
                new SimpleGrantedAuthority("ROLE_" + foundUser.getRole().toUpperCase());

        // Validación manual del estado locked (Spring ya no ofrece accountNonLocked)
        if (foundUser.isLocked()) {
            throw new UsernameNotFoundException("La cuenta está bloqueada");
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(foundUser.getEmail())
                .password(foundUser.getPassword())
                .authorities(Collections.singletonList(authority))
                .disabled(!foundUser.isActive())        // único estado permitido en Spring Security 6
                .build();
    }
}
