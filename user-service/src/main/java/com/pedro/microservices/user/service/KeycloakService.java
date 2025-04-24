package com.pedro.microservices.user.service;

import com.pedro.microservices.user.client.KeycloakClient;
import com.pedro.microservices.user.dto.LoginRequest;
import com.pedro.microservices.user.dto.RegisterRequest;
import com.pedro.microservices.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KeycloakService {

    private final KeycloakClient keycloakClient;

    public void register(RegisterRequest registerRequest) {
        User user = User.builder()
        .username(registerRequest.username())
                .email(registerRequest.email())
                .firstName(registerRequest.firstName())
                .lastName(registerRequest.lastName())
                .credentials(registerRequest.password())
                .build();
        keycloakClient.register(user);
    }

    public String login(LoginRequest loginRequest) {
        return keycloakClient.login(loginRequest);
    }

}
