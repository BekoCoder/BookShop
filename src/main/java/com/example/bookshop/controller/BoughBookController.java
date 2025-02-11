package com.example.bookshop.controller;

import com.example.bookshop.dto.ResponseDto;
import com.example.bookshop.service.BoughtBookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bought-books")
@RequiredArgsConstructor
@Tag(name = "bought-controller", description = "Kitob sotib olish uchun API lar")
public class BoughBookController {
    private final BoughtBookService boughtBookService;

    @Operation(summary = "Kitob sotib olish")
    @PostMapping("/save/{id}")
    public ResponseEntity<ResponseDto<String>> save(@PathVariable Long id, @RequestParam(name = "quantity") Integer quantity) {
        return ResponseEntity.ok(boughtBookService.save(id, quantity));
    }
}
