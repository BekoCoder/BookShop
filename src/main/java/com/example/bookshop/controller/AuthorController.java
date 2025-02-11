package com.example.bookshop.controller;

import com.example.bookshop.dto.AuthorsDto;
import com.example.bookshop.dto.ResponseDto;
import com.example.bookshop.service.AuthorsService;
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
@RequestMapping("/author")
@RequiredArgsConstructor
@Tag(name = "author-controller", description = "Kitob mualliflari uchun API lar")
@Slf4j
public class AuthorController {
    private final AuthorsService authorsService;

    @Operation(summary = "Avtor qo'shish")
    @PostMapping("/create")
    public ResponseEntity<ResponseDto<AuthorsDto>> create(@RequestBody AuthorsDto authorsDto) {
        log.trace("Accessing /author/create" + authorsDto);
        ResponseDto<AuthorsDto> authorsDtoResponseDto = authorsService.addAuthor(authorsDto);
        log.trace("returned front-end /author/create" + authorsDtoResponseDto);
        return ResponseEntity.ok(authorsDtoResponseDto);
    }

    @Operation(summary = "Avtorni yangilash")
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto<AuthorsDto>> update(@PathVariable("id") Long id, @RequestBody AuthorsDto authorsDto) {
        log.trace("Accessing /author/update/{}", id);
        ResponseDto<AuthorsDto> authorsDtoResponseDto = authorsService.updateAuthor(authorsDto, id);
        log.trace("returned front-end /author/update/{}", authorsDtoResponseDto);
        return ResponseEntity.ok(authorsDtoResponseDto);
    }

    @Operation(summary = "Id orqali avtorni olish")
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ResponseDto<AuthorsDto>> getById(@PathVariable("id") Long id) {
        log.trace("Accessing /author/get-by-id/{}", id);
        return ResponseEntity.ok(authorsService.getAuthor(id));
    }

    @Operation(summary = "Barcha avtorlarni olish")
    @GetMapping("/get-all")
    public ResponseEntity<List<AuthorsDto>> getAll(Pageable pageable) {
        Page<AuthorsDto> page = authorsService.getAuthors(pageable);
        return ResponseEntity.ok(page.getContent());
    }

    @Operation(summary = "Id orqali avtorni o'chirish")
    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<ResponseDto<String>> delete(@PathVariable("id") Long id) {
        log.trace("Accessing /author/delete-by-id/{}", id);
        ResponseDto<String> responseDto = authorsService.deleteAuthor(id);
        log.trace("Returned front-end /author/delete-by-id/{}", id);
        return ResponseEntity.ok(responseDto);
    }

    @Operation(summary = "Kitobni muallifga biriktirish")
    @PutMapping("/assign/{bookId}/{authorId}")
    public ResponseEntity<ResponseDto<String>> assign(@PathVariable("bookId") Long bookId, @PathVariable("authorId") Long authorId) {
        log.trace("Accessing /author/assign/{}", authorId);
        return ResponseEntity.ok(authorsService.assignBookToAuthor(bookId, authorId));
    }
}
