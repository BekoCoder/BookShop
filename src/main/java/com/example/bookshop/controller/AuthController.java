package com.example.bookshop.controller;

import com.example.bookshop.dto.*;
import com.example.bookshop.repository.dao.LastWeek;
import com.example.bookshop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "auth-controller", description = "Foydalanuvchilarni ro'yhatdan o'tkazish uchun API lar")
public class AuthController {
    private final UserService userService;
    private final LastWeek lastWeek;

    @Operation(summary = "Foydalanuvchini ro'yhatdan o'tkazish")
    @PostMapping("/register")
    public ResponseEntity<ResponseDto<UserDto>> register(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.save(userDto));
    }

    @Operation(summary = "Foydalanuvchini login qilish")
    @PostMapping("/login")
    public ResponseEntity<ResponseDto<JwtResponceDto>> login(@RequestBody LoginRequestDto dto) {
        return ResponseEntity.ok(userService.login(dto));
    }

}
