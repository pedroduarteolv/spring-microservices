package com.pedro.microservices.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterRequest (
        @NotBlank String username,
        @NotBlank @Email String email,
        @NotBlank String password,
        String firstName,
        String lastName
){}
