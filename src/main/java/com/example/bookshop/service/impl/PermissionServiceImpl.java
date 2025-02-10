package com.example.bookshop.service.impl;

import com.example.bookshop.dto.PermissionsDto;
import com.example.bookshop.dto.ResponseDto;
import com.example.bookshop.entity.Permissions;
import com.example.bookshop.entity.Roles;
import com.example.bookshop.exceptions.PermissionException;
import com.example.bookshop.exceptions.RoleException;
import com.example.bookshop.repository.PermissionsRepository;
import com.example.bookshop.repository.RolesRepository;
import com.example.bookshop.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
    private final PermissionsRepository permissionsRepository;
    private final ModelMapper mapper;
    private final RolesRepository rolesRepository;

    @Override
    public ResponseDto<PermissionsDto> addPermission(PermissionsDto permissionsDto) {
        ResponseDto<PermissionsDto> responseDto = new ResponseDto<>();
        Permissions permissions = mapper.map(permissionsDto, Permissions.class);
        if (isPermissionExist(permissions.getName())) {
            throw new PermissionException("Bunday permission allaqachon mavjud");
        }
        permissionsRepository.save(permissions);
        responseDto.setSuccess(true);
        responseDto.setMessage("Permission muvaffaqiyatli kiritildi");
        responseDto.setRecordsTotal(1L);
        responseDto.setData(mapper.map(permissions, PermissionsDto.class));
        return responseDto;
    }

    @Override
    public ResponseDto<PermissionsDto> updatePermission(PermissionsDto permissionsDto, Long id) {
        ResponseDto<PermissionsDto> responseDto = new ResponseDto<>();
        Permissions permissions = permissionsRepository.findById(id).orElseThrow(() -> new PermissionException("Bunday permission mavjud emas !!!"));
        if (permissions.getIsDeleted() == 1) {
            throw new PermissionException("Permission o'chirilgan !!!");
        }
        permissions.setName(permissionsDto.getName());
        permissionsRepository.save(permissions);
        responseDto.setSuccess(true);
        responseDto.setMessage("Permission muvaffaqiyatli yangilandi");
        responseDto.setRecordsTotal(1L);
        responseDto.setData(mapper.map(permissions, PermissionsDto.class));
        return responseDto;
    }

    @Override
    public ResponseDto<String> deletePermission(Long id) {
        ResponseDto<String> responseDto = new ResponseDto<>();
        Permissions permissions = permissionsRepository.findById(id).orElseThrow(() -> new PermissionException("Bunday permission mavjud emas !!!"));
        if (permissions.getIsDeleted() == 1) {
            throw new PermissionException("Permission allaqachon o'chirilgan !!!");
        }
        permissions.setIsDeleted(1);
        permissionsRepository.save(permissions);
        responseDto.setSuccess(true);
        responseDto.setMessage("Permission muvaffaqiyatli o'chirildi !!!");
        responseDto.setRecordsTotal(1L);
        return responseDto;
    }

    @Override
    public ResponseDto<PermissionsDto> getPermission(Long id) {
        ResponseDto<PermissionsDto> responseDto = new ResponseDto<>();
        Permissions permissions = permissionsRepository.findById(id).orElseThrow(() -> new PermissionException("Bunday permission mavjud emas !!!"));
        if (Objects.isNull(permissions) || permissions.getIsDeleted() == 1) {
            throw new PermissionException("Permission topilmadi !!!");
        }
        responseDto.setSuccess(true);
        responseDto.setRecordsTotal(1L);
        responseDto.setMessage("Permission muvaffaqiyatli qaytarildi !!!");
        responseDto.setData(mapper.map(permissions, PermissionsDto.class));
        return responseDto;
    }

    @Override
    public ResponseDto<String> assignPermissionToRole(Long permissionId, Long roleId) {
        Roles roles = rolesRepository.findById(roleId).orElseThrow(() -> new RoleException("Bunday role mavjud emas !!!"));
        Permissions permissions = permissionsRepository.findById(permissionId).orElseThrow(() -> new PermissionException("Bunday permission mavjud emas !!!"));
        Set<Permissions> permissions1 = roles.getPermissions();
        if(!permissions1.contains(permissions)) {
            permissions1.add(permissions);
        }
        roles.setPermissions(permissions1);
        rolesRepository.save(roles);
        ResponseDto<String> responseDto = new ResponseDto<>();
        responseDto.setSuccess(true);
        responseDto.setMessage("Permission rolga biriktirildi !!!");
        responseDto.setRecordsTotal(1L);
        return responseDto;
    }

    @Override
    public Page<PermissionsDto> getPermissions(Pageable pageable) {
        return permissionsRepository.findAllByIsDeleted(pageable, 0).map((permissionsDto) -> mapper.map(permissionsDto, PermissionsDto.class));
    }

    private boolean isPermissionExist(String name) {
        return permissionsRepository.findByName(name).isPresent();
    }
}
