package com.example.bookshop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "basic dto")
@Entity
public class BasicDto {
    @Id
    @Schema(description = "user id")
    private Long id;

    @Schema(description = "book count")
    private Integer bookCount;

    @Schema(description = "first name")
    private String firstName;

    @Schema(description = "last name")
    private String lastName;
}
