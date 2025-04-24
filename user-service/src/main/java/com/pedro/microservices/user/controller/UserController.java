package com.pedro.microservices.user.controller;

import com.pedro.microservices.user.dto.RegisterRequest;
import com.pedro.microservices.user.service.KeycloakService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/keycloak")
@RequiredArgsConstructor
public class UserController {

    private final KeycloakService keycloakService;

    @PostMapping("/register")
    public void register(@RequestBody RegisterRequest registerRequest) {
        keycloakService.register(registerRequest);
    }

}
