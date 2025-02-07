package com.example.bookshop.controller;

import com.example.bookshop.dto.ResponseDto;
import com.example.bookshop.dto.RolesDto;
import com.example.bookshop.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
@Tag(name = "role-controller", description = "Rollar uchun API lar")
public class RoleController {
    private final RoleService roleService;

    @Operation(summary = "role yaratish")
    @PostMapping("/create")
    public ResponseEntity<ResponseDto<RolesDto>> create(@RequestBody RolesDto rolesDto) {
        return ResponseEntity.ok(roleService.addRole(rolesDto));
    }

    @Operation(summary = "role yangilash")
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDto<RolesDto>> update(@RequestBody RolesDto rolesDto, @PathVariable Long id) {
        return ResponseEntity.ok(roleService.updateRole(rolesDto, id));
    }

    @Operation(summary = "role ni id orqali olish")
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ResponseDto<RolesDto>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.getRoleById(id));
    }

    @Operation(summary = "role ni id orqali o'chirish")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDto<String>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.deleteRole(id));
    }

    @Operation(description = "Barcha rollarni olish")
    @GetMapping("/get-all")
    public ResponseEntity<List<RolesDto>> getAll(Pageable pageable) {
        Page<RolesDto> roles = roleService.getRoles(pageable);
        return ResponseEntity.ok(roles.getContent());
    }
}
