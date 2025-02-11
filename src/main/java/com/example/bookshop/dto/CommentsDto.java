package com.example.bookshop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Kitoblar haqida izohlar")
public class CommentsDto {
    private String comments;

}
