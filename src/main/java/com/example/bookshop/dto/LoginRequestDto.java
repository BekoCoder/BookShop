package com.example.bookshop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Kirish so'rovi")
public class LoginRequestDto {
    @Schema(description = "Foydalanuvchi nomi")
    private String username;
    @Schema(description = "Foydalanuvchi paroli")
    private String password;
}
