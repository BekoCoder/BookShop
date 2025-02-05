package com.example.bookshop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Foydalanuvchi ma'lumotlari")
public class UserDto {
    @Schema(description = "Foydalanuvchi Ismi")
    private String firstName;
    @Schema(description = "Foydalanuvchi Familiyasi")
    private String lastName;
    @Schema(description = "Foydalanuvchi Usernami")
    private String username;
    @Schema(description = "Foydalanuvchi Paroli")
    private String password;
}
