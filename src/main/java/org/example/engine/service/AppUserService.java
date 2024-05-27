package org.example.engine.service;

import lombok.RequiredArgsConstructor;
import org.example.engine.dto.RequestAppUserDto;
import org.example.engine.entity.AppUserEntity;
import org.example.engine.repository.AppUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<?> signUp(RequestAppUserDto requestAppUserDto) {

        if(userRepository.existsAppUserByUsername(requestAppUserDto.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with same email is already created");
        } else {
            userRepository.save(
                    AppUserEntity
                            .builder()
                            .username(requestAppUserDto.getUsername())
                            .password(passwordEncoder.encode(requestAppUserDto.getPassword()))
                            .build()
            );

            return ResponseEntity.ok().build();
        }
    }
}
