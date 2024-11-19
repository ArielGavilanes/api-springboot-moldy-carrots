package com.proyecto.moldy_carrots.auth.response;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;

    private long expiresIn;
}
