package org.example.engine.service.implementation;

import org.example.engine.entity.AppUserEntity;
import org.example.engine.repository.AppUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsServiceImpl implements UserDetailsService {
    private final AppUserRepository repository;

    public AppUserDetailsServiceImpl(AppUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUserEntity user = repository
                .findAppUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Not found"));

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}