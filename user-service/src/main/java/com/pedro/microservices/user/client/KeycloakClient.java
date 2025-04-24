package com.pedro.microservices.user.client;

import com.pedro.microservices.user.dto.LoginRequest;
import com.pedro.microservices.user.dto.RegisterRequest;
import com.pedro.microservices.user.model.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

public interface KeycloakClient {

    @PostExchange("/realms/spring-microservices-realm/protocol/openid-connect/token")
    String login(@RequestBody LoginRequest loginRequest);

    @PostExchange("/realms/spring-microservices-realm/users")
    void register(@RequestBody User user);
}
