package com.example.bookshop.service;

import com.example.bookshop.dto.ResponseDto;
import com.example.bookshop.dto.RolesDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleService {
    ResponseDto<RolesDto> addRole(RolesDto rolesDto);

    ResponseDto<RolesDto> updateRole(RolesDto rolesDto, Long id);

    ResponseDto<String> deleteRole(Long id);

    ResponseDto<RolesDto> getRoleById(Long id);

    Page<RolesDto> getRoles(Pageable pageable);

    ResponseDto<String> assignRoleToUser(Long userId, Long roleId);
}
