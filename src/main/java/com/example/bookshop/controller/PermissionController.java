package com.example.bookshop.controller;

import com.example.bookshop.dto.PermissionsDto;
import com.example.bookshop.dto.ResponseDto;
import com.example.bookshop.service.PermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
@Tag(name="permission-controller", description = "Ruxsatlar uchun API lar")
public class PermissionController {
    private final PermissionService permissionService;

    @Operation(summary = "Ruxsat yaratish")
    @PostMapping("/create")
    public ResponseEntity<ResponseDto<PermissionsDto>> create(@RequestBody PermissionsDto permissionsDto) {
        return ResponseEntity.ok(permissionService.addPermission(permissionsDto));
    }

    @Operation(summary = "Ruxsatni rolga biriktirish")
    @PutMapping("/assign/{permissionId}/{roleId}")
    public ResponseEntity<ResponseDto<String >> assign(@PathVariable Long permissionId, @PathVariable Long roleId) {
        return ResponseEntity.ok(permissionService.assignPermissionToRole(permissionId, roleId));
    }
}
