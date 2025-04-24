package com.pedro.microservices.user.dto;

public record LoginRequest (
         String clientId,
         String clientSecret,
         String username,
         String password,
         String grantType
){
}
