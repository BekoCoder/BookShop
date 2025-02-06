package com.example.bookshop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Kitoblar ma'lumotlari")
public class BooksDto {
    @Schema(description = "Kitob nomi")
    private String title;
    @Schema(description = "Kitob tavsifi")
    private String description;
    @Schema(description = "Kitob narxi")
    private Double price;
    @Schema(description = "Kitob miqdori")
    private Integer quantity;
}
