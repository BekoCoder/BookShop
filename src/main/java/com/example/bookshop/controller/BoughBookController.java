package com.example.bookshop.controller;

import com.example.bookshop.dto.BoughtBooksDto;
import com.example.bookshop.dto.ResponseDto;
import com.example.bookshop.service.BoughtBookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Operation(summary = "Sotib olingan kitobni id orqali olish")
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ResponseDto<BoughtBooksDto>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(boughtBookService.get(id));
    }

    @Operation(summary = "Barcha sotib olingan kitoblarni ko'rish")
    @GetMapping("/get-all")
    public ResponseEntity<List<BoughtBooksDto>> getAll(Pageable pageable) {
        Page<BoughtBooksDto> page = boughtBookService.getAll(pageable);
        return ResponseEntity.ok(page.getContent());

    }
}
