package com.fatihkarakus.user_service.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Set;

@Data
public class RoleDto {

    @Schema(
            description = "ROLE Name",
            name = "name",
            type = "string",
            example = "MEMBER")
    private String name;

    @Schema(description = "Role ait izinler listesi")
    private Set<PermissionDto> permissions;
}
