package com.pedro.microservices.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {

    private String username;
    private String email;
    private boolean enabled = true;
    private String firstName;
    private String lastName;
    private String credentials;
}
