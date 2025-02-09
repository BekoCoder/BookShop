package com.example.bookshop.service;

import com.example.bookshop.dto.PermissionsDto;
import com.example.bookshop.dto.ResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PermissionService {
    ResponseDto<PermissionsDto> addPermission(PermissionsDto permissionsDto);

    ResponseDto<PermissionsDto> updatePermission(PermissionsDto permissionsDto, Long id);

    ResponseDto<String> deletePermission(Long id);

    ResponseDto<PermissionsDto> getPermission(Long id);

    Page<PermissionsDto> getPermissions(Pageable pageable);

    ResponseDto<String> assignPermissionToRole(Long permissionId, Long roleId);
}
