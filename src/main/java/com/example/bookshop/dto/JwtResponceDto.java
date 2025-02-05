package com.example.bookshop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Jwt Responce")
@Builder
public class JwtResponceDto {

    private String token;
    private String username;
    private String password;
}
