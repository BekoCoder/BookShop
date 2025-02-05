package com.example.bookshop.controller;

import com.example.bookshop.dto.JwtResponceDto;
import com.example.bookshop.dto.LoginRequestDto;
import com.example.bookshop.dto.ResponseDto;
import com.example.bookshop.dto.UserDto;
import com.example.bookshop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@Tag(name = "admin-controller", description = "Admin Panel uchun API lar")
@Slf4j
public class AdminController {
    private final UserService userService;

    @Operation(summary = "Foydalanuvchini ro'yhatdan o'tkazish")
    @PostMapping("/register")
    public ResponseEntity<ResponseDto<UserDto>> register(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.save(userDto));
    }

    @Operation(summary = "Foydalanuvchini login qilish")
    @PostMapping("/login")
    public ResponseEntity<ResponseDto<JwtResponceDto>> login(@RequestBody LoginRequestDto dto){
        return ResponseEntity.ok(userService.login(dto));
    }


}
