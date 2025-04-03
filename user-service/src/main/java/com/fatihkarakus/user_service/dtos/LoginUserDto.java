package com.fatihkarakus.user_service.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LoginUserDto {
    @Schema(
            description = "Username",
            name = "email",
            type = "string",
            example = "admin@admin.com")
    private String email;

    @Schema(
            description = "Password",
            name = "password",
            type = "string",
            example = "admin")
    private String password;
}
