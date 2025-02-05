package com.example.bookshop.controller;

import com.example.bookshop.dto.ResponseDto;
import com.example.bookshop.dto.UserDto;
import com.example.bookshop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Tag(name = "admin-controller", description = "Admin Panel uchun API lar")
@Slf4j
public class AdminController {

    private final UserService userService;

    @Operation(summary = "Id orqali olish")
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

}
