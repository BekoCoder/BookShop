package com.example.bookshop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Foydalanuvchi kitoblarini ko'rish uchun DTO")
public class UserBookDto {
    @Schema(description = "FirstName")
    private String firstName;

    @Schema(description = "LastName")
    private String lastName;

    @Schema(description = "Username")
    private String username;

    @Schema(description = "Kitob nomi")
    private String title;

    @Schema(description = "Kitob narxi")
    private Double price;

    @Schema(description = "Umumiy narxi")
    private Double totalPrice;
}
