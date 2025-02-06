package com.example.bookshop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Kitob muallifi haqida ma'lumotlar")
public class AuthorsDto {
    @Schema(description = "Kitob muallifi nomi")
    private String name;

}
