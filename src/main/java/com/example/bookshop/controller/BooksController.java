package com.example.bookshop.controller;

import com.example.bookshop.dto.BooksDto;
import com.example.bookshop.dto.ResponseDto;
import com.example.bookshop.service.BooksService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
@Tag(name = "books-controller", description = "Kitoblar uchun API lar")
@Slf4j
public class BooksController {
    private final BooksService booksService;

    @Operation(summary = "Kitob qo'shish")
    @PostMapping("/create")
    public ResponseEntity<ResponseDto<BooksDto>> createBook(@RequestBody BooksDto booksDto) {
        log.info("Create book");
        return ResponseEntity.ok(booksService.addBook(booksDto));
    }

    @Operation(summary = "Id orqali kitobni olish")
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ResponseDto<BooksDto>> getBookById(@PathVariable Long id) {
        log.info("Get book by id");
        return ResponseEntity.ok(booksService.getBookById(id));
    }

    @Operation(summary = "Id orqali kitobni o'chirish")
    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<ResponseDto<String>> deleteBookById(@PathVariable Long id) {
        log.info("Delete book by id");
        return ResponseEntity.ok(booksService.deleteBook(id));
    }

    @Operation(summary = "Barcha kitoblar ro'yhatini olish")
    @GetMapping("/get-all")
    public ResponseEntity<List<BooksDto>> getAllBooks(Pageable pageable) {
        log.info("Get all books");
        Page<BooksDto> allBooks = booksService.getAllBooks(pageable);
        return ResponseEntity.ok(allBooks.getContent());
    }


    @Operation(summary = "Id orqali kitobni yangilash")
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto<BooksDto>> updateBook(@PathVariable Long id, @RequestBody BooksDto booksDto) {
        log.info("Update book");
        return ResponseEntity.ok(booksService.updateBook(booksDto, id));
    }
}
