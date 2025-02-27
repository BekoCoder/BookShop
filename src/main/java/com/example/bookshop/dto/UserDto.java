package com.example.bookshop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Schema(description = "Foydalanuvchi ma'lumotlari")
@Entity
public class UserDto {
    @Id
    private Long id;
    @Schema(description = "Foydalanuvchi Ismi")
    private String firstName;
    @Schema(description = "Foydalanuvchi Familiyasi")
    private String lastName;
    @Schema(description = "Foydalanuvchi Usernami")
    private String username;
    @Schema(description = "Foydalanuvchi Paroli")
    private String password;
}
