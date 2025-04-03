package com.fatihkarakus.user_service.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PermissionDto {

    @Schema(description = "İzin Id", example = "1")
    private Integer id;

    @Schema(description = "Permission adı", example = "READ_PRIVILEGES")
    @NotNull(message = "Permission name cannot be null")
    private String name;
}
