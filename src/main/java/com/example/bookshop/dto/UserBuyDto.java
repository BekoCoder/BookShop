package com.example.bookshop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Schema(description = "Foydalanuvchi buyurtma ma'lumotlari")
@Entity
public class UserBuyDto {
    @Id
    @Schema(description = "Unikal kod")
    private Long id;

    @Schema(description = "Foydalanuvchi Ismi")
    private String firstName;

    @Schema(description = "Foydalanuvchi Familiyasi")
    private String lastName;

    @Schema(description = "Umumiy summa")
    private Integer totalPrice;
}
