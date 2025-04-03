package com.fatihkarakus.user_service.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RegisterUserDto {
    @Schema(
            description = "Username",
            name = "email",
            type = "string",
            example = "john@doe.com")
    private String email;

    @Schema(
            description = "Password",
            name = "password",
            type = "string",
            example = "123456")
    private String password;

    @Schema(
            description = "Full Name",
            name = "fullName",
            type = "string",
            example = "John Doe")
    private String fullName;
}
