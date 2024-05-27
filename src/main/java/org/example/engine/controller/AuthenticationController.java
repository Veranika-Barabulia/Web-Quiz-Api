package org.example.engine.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.engine.dto.RequestAppUserDto;
import org.example.engine.service.AppUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AppUserService appUserService;

    @PostMapping("/api/register")
    public ResponseEntity<?> signUp(@Valid @RequestBody RequestAppUserDto request) {
        return appUserService.signUp(request);
    }

}
