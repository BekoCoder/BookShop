package com.example.bookshop.controller;

import com.example.bookshop.dto.CommentsDto;
import com.example.bookshop.dto.ResponseDto;
import com.example.bookshop.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@Tag(name = "comment-controller", description = "Kitobga komentariya yozish uchun API lar")
@Slf4j
public class CommentController {
    private final CommentService commentService;

    @Operation(summary = "Kitobga komentariya yozish")
    @PostMapping("/book-write/{bookId}")
    public ResponseEntity<ResponseDto<CommentsDto>> bookWrite(@RequestBody CommentsDto commentsDto, @PathVariable Long bookId) {
        log.info("Book write operation started");
        return ResponseEntity.ok(commentService.addComment(commentsDto, bookId));
    }


    @Operation(summary = "Komentariyani id orqali olish")
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ResponseDto<CommentsDto>> getById(@PathVariable Long id) {
        log.info("Get comment by id operation started");
        return ResponseEntity.ok(commentService.getComment(id));
    }

}
