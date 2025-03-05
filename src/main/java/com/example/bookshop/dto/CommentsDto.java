package com.example.bookshop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Schema(description = "Kitoblar haqida izohlar")
@Entity
public class CommentsDto {
    @Id
    @Schema(description = "Unikal kodi")
    private Long id;

    @Schema(description = "FirstName")
    private String firstName;

    @Schema(description = "LastName")
    private String lastName;

    @Schema(description = "Komentariyalar")
    private String comments;

    @Schema(description = "Kitob nomi")
    private String title;

}
