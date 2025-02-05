package com.example.bookshop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Sotilgan kitoblar")
public class BoughtBooksDto {
    @Schema(description = "Kitob umumiy narxi")
    private Double totalPrice;
    @Schema(description = "Kitoblar soni")
    private Integer count;
}
