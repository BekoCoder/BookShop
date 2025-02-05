package com.example.bookshop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Rollar")
public class RolesDto {
    @Schema(description = "Rol nomi")
    private String name;

}
