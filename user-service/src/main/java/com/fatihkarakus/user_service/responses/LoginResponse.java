package com.fatihkarakus.user_service.responses;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private long expiresIn;

    public String getToken() {
        return token;
    }
}
