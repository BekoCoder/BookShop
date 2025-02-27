package com.example.bookshop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "user basic dto")
@Entity
public class UserBasicDto {
    @Id
    @Schema(description = "Unikal kod")
    private Long id;
    @Schema(description = "Foydalanuvchi Ismi")
    private String firstName;
    @Schema(description = "Foydalanuvchi Familiyasi")
    private String lastName;
    @Schema(description = "Foydalanuvchi Usernami")
    private String username;
    @Schema(description = "ro'yhatdan o'tgan sanasi")
    private LocalDateTime createdDate;
}
