package com.example.bookshop.controller;

import com.example.bookshop.dto.PermissionsDto;
import com.example.bookshop.dto.ResponseDto;
import com.example.bookshop.service.PermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permission")
@RequiredArgsConstructor
@Tag(name = "permission-controller", description = "Ruxsatlar uchun API lar")
public class PermissionController {
    private final PermissionService permissionService;

    @Operation(summary = "Ruxsat yaratish")
    @PostMapping("/create")
    public ResponseEntity<ResponseDto<PermissionsDto>> create(@RequestBody PermissionsDto permissionsDto) {
        return ResponseEntity.ok(permissionService.addPermission(permissionsDto));
    }

    @Operation(summary = "Ruxsatni yangilash")
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto<PermissionsDto>> update(@RequestBody PermissionsDto permissionsDto, @PathVariable Long id) {
        return ResponseEntity.ok(permissionService.updatePermission(permissionsDto, id));
    }

    @Operation(summary = "Ruxsatni id orqali olish")
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ResponseDto<PermissionsDto>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(permissionService.getPermission(id));
    }

    @Operation(summary = "Barcha ruxsatlarni olish")
    @GetMapping("/get-all")
    public ResponseEntity<List<PermissionsDto>> getAll(Pageable pageable) {
        Page<PermissionsDto> page = permissionService.getPermissions(pageable);
        return ResponseEntity.ok(page.getContent());
    }

    @Operation(summary = "Ruxsatni id orqali o'chirish")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto<String>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(permissionService.deletePermission(id));
    }

    @Operation(summary = "Ruxsatni rolga biriktirish")
    @PutMapping("/assign/{permissionId}/{roleId}")
    public ResponseEntity<ResponseDto<String>> assign(@PathVariable Long permissionId, @PathVariable Long roleId) {
        return ResponseEntity.ok(permissionService.assignPermissionToRole(permissionId, roleId));
    }
}
