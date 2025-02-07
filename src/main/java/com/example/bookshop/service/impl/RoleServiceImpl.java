package com.example.bookshop.service.impl;

import com.example.bookshop.dto.ResponseDto;
import com.example.bookshop.dto.RolesDto;
import com.example.bookshop.entity.Roles;
import com.example.bookshop.exceptions.RoleException;
import com.example.bookshop.repository.RolesRepository;
import com.example.bookshop.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RolesRepository rolesRepository;
    private final ModelMapper mapper;

    @Override
    public ResponseDto<RolesDto> addRole(RolesDto rolesDto) {
        ResponseDto<RolesDto> responseDto = new ResponseDto<>();
        Roles roles = mapper.map(rolesDto, Roles.class);
        if (isRoleExist(roles.getName())) {
            throw new RoleException("Bunday role mavjud !!!");
        }
        rolesRepository.save(roles);
        responseDto.setSuccess(true);
        responseDto.setMessage("Role qo'shildi !!!");
        responseDto.setRecordsTotal(rolesRepository.count());
        responseDto.setData(mapper.map(roles, RolesDto.class));
        return responseDto;

    }

    @Override
    public ResponseDto<RolesDto> updateRole(RolesDto rolesDto, Long id) {
        ResponseDto<RolesDto> responseDto = new ResponseDto<>();
        Roles roles = rolesRepository.findById(id).orElseThrow(() -> new RoleException("Bunday role mavjud emas !!!"));
        if (isRoleExist(roles.getName())) {
            throw new RoleException("Bunday role mavjud !!!");
        }
        if (roles.getIsDeleted() == 1) {
            throw new RoleException("Role o'chirilgan !!!");
        }
        roles.setName(rolesDto.getName());
        rolesRepository.save(roles);
        responseDto.setSuccess(true);
        responseDto.setMessage("Role yangilandi !!!");
        responseDto.setRecordsTotal(rolesRepository.count());
        responseDto.setData(mapper.map(roles, RolesDto.class));
        return responseDto;
    }

    @Override
    public ResponseDto<String> deleteRole(Long id) {
        ResponseDto<String> responseDto = new ResponseDto<>();
        Roles roles = rolesRepository.findById(id).orElseThrow(() -> new RoleException("Bunday role mavjud emas !!!"));
        if (roles.getIsDeleted() == 1) {
            throw new RoleException("Role o'chirilgan !!!");
        }
        roles.setIsDeleted(1);
        responseDto.setSuccess(true);
        responseDto.setMessage("Role o'chirildi !!!");
        responseDto.setRecordsTotal(rolesRepository.count());
        return responseDto;
    }

    @Override
    public ResponseDto<RolesDto> getRoleById(Long id) {
        ResponseDto<RolesDto> responseDto = new ResponseDto<>();
        Roles roles = rolesRepository.findById(id).orElseThrow(() -> new RoleException("Bunday role mavjud emas !!!"));
        if (roles.getIsDeleted() == 1) {
            throw new RoleException("Role o'chirilgan !!!");
        }
        responseDto.setSuccess(true);
        responseDto.setMessage("Role qaytarildi !!!");
        responseDto.setRecordsTotal(rolesRepository.count());
        responseDto.setData(mapper.map(roles, RolesDto.class));
        return responseDto;

    }

    @Override
    public Page<RolesDto> getRoles(Pageable pageable) {
        return rolesRepository.findAllByIsDeleted(pageable, 0).map((element) -> mapper.map(element, RolesDto.class));
    }

    private boolean isRoleExist(String name) {
        return rolesRepository.findByName(name).isPresent();
    }
}
