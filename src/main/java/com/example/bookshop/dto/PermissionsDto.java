package com.example.bookshop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Ruxsatlar")
public class PermissionsDto {
    @Schema(description = "Ruxsat nomi")
    private String name;

}
