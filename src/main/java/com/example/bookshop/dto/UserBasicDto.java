package com.example.bookshop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "user basic dto")
public class UserBasicDto {
    @Schema(description = "Foydalanuvchi Ismi")
    private String firstName;
    @Schema(description = "Foydalanuvchi Familiyasi")
    private String lastName;
    @Schema(description = "Foydalanuvchi Usernami")
    private String userName;
    @Schema(description = "ro'yhatdan o'tgan sanasi")
    private LocalDateTime createdDate;
}
