package com.example.bookshop.controller;

import com.example.bookshop.dto.*;
import com.example.bookshop.service.CommentService;
import com.example.bookshop.service.UserService;
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
@RequestMapping("/admin")
@RequiredArgsConstructor
@Tag(name = "admin-controller", description = "Admin Panel uchun API lar")
@Slf4j
public class AdminController {

    private final UserService userService;
    private final CommentService commentService;

    @Operation(summary = "Foydalanuvchilarni id orqali olish")
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ResponseDto<UserDto>> getById(@PathVariable Long id) {
        log.trace("Accessing GET /admin/get-by-id/{}", id);
        ResponseDto<UserDto> userById = userService.getById(id);
        log.trace("Returned to fron-end: {} ", userById);
        return ResponseEntity.ok(userById);
    }

    @Operation(summary = "Foydalanuvchilarni yangilash")
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto<UserDto>> update(@PathVariable Long id, @RequestBody UserDto userDto) {
        log.trace("Accessing PUT /admin/update/{}", id);
        ResponseDto<UserDto> userById = userService.update(userDto, id);
        log.trace("Returned to fron-end: {} ", userById);
        return ResponseEntity.ok(userById);
    }

    @Operation(summary = "Foydalanuvchilarni id orqali o'chirish")
    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<ResponseDto<String>> delete(@PathVariable Long id) {
        log.trace("Accessing DELETE /admin/delete-by-id/{}", id);
        return ResponseEntity.ok(userService.deleteById(id));
    }

    @Operation(summary = "Barcha foydalanuvchilarni olish")
    @GetMapping("/gel-all")
    public ResponseEntity<List<UserDto>> getAll(Pageable pageable) {
        log.trace("Accessing GET /admin/gel-all");
        Page<UserDto> page = userService.getAllUser(pageable);
        log.trace("Returned to fron-end: {} ", page.getSize());
        return ResponseEntity.ok(page.getContent());
    }


    @Operation(summary = "Oxirgi haftada ro'yhatdan o'tgan foydalanuvchilar")
    @GetMapping("/get")
    public ResponseEntity<List<UserBasicDto>> getUsers() {
        log.trace("Accessing GET /admin/get");
        return ResponseEntity.ok(userService.userWeek());
    }

    @Operation(summary = "Kitob sotib olganlarni ko'rish")
    @GetMapping("/get-bought-books/{userId}")
    public ResponseEntity<ResponseDto<List<UserBookDto>>> getBoughtBooks(@PathVariable Long userId) {
        log.trace("Accessing GET /admin/get-bought-books/{}", userId);
        return ResponseEntity.ok(userService.getUserBooks(userId));
    }

    @Operation(summary = "Har bir oyda ro'yhatdan o'tgan foydalanuvchilar")
    @GetMapping("/get-monthly-users")
    public ResponseEntity<ResponseDto<List<UserDto>>> getMonthlyUsers(@RequestParam(name = "month") Integer month) {
        log.trace("Accessing GET /admin/get-monthly-users");
        return ResponseEntity.ok(userService.getEveryMonthUsers(month));
    }

    @Operation(summary = "Eng faol foydalanuvchilarni ko'rish. Bunda eng ko'p kitob olgan foydalanuvchilarni ko'rish")
    @GetMapping("/most-active-users")
    public ResponseEntity<ResponseDto<List<BasicDto>>> mostActiveUsers() {
        log.trace("Accessing GET /admin/most-active-users");
        return ResponseEntity.ok(userService.mostActiveUsers());
    }

    @Operation(summary = "Eng faol foydalanuvchilarni ko'rish. Bunda foydalanuvchilarning eng summada kitob olganlarini ko'rish")
    @GetMapping("/most-active-users-by-sum")
    public ResponseEntity<ResponseDto<List<UserBuyDto>>> mostActiveUsersBySum() {
        log.trace("Accessing GET /admin/most-active-users-by-sum");
        return ResponseEntity.ok(userService.getUserBuys());
    }

    @Operation(summary = "Barcha komentariyalarni olish")
    @GetMapping("/get-all-comments")
    public ResponseEntity<ResponseDto<List<CommentsDto>>> getAllComment() {
        log.info("Get all comments operation started");
        return ResponseEntity.ok(userService.getComments());
    }


}
