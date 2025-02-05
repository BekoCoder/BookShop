package com.example.bookshop.service.impl;

import com.example.bookshop.dto.JwtResponceDto;
import com.example.bookshop.dto.LoginRequestDto;
import com.example.bookshop.dto.ResponseDto;
import com.example.bookshop.dto.UserDto;
import com.example.bookshop.entity.User;
import com.example.bookshop.exceptions.CustomException;
import com.example.bookshop.jwt.JwtService;
import com.example.bookshop.repository.UserRepository;
import com.example.bookshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public ResponseDto<UserDto> save(UserDto userDto) {
        ResponseDto<UserDto> responseDto = new ResponseDto<>();
        User user = mapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        if (!checkPassword(userDto.getPassword())) {
            throw new CustomException("Parol uzunligi 5 va 16 uzunlik orasida bo'lishi kerak");
        }
        if (isExistUser(userDto.getUsername())) {
            throw new CustomException("Bunday foydalanuvchi oldin ro'yhatdan o'tgan!!! ");
        }
        userRepository.save(user);
        responseDto.setSuccess(true);
        responseDto.setMessage("Foydalanuvchi saqlandi");
        responseDto.setRecordsTotal(1L);
        responseDto.setData(mapper.map(user, UserDto.class));
        return responseDto;

    }

    @Override
    public ResponseDto<JwtResponceDto> login(LoginRequestDto dto) {
        ResponseDto<JwtResponceDto> responseDto = new ResponseDto<>();
        User user = userRepository.findByUsername(dto.getUsername()).orElseThrow(() -> new CustomException("Foydalanuvchi topilmadi!!!"));
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new CustomException("Parol xato kiritildi");
        }
        if (user.getIsDeleted() == 1) {
            throw new CustomException("Foydalanuvchi bloklangan");
        }
        String token = jwtService.generateToken(user);
        responseDto.setSuccess(true);
        responseDto.setMessage("Foydalanuvchi tizimga kirdi");
        responseDto.setRecordsTotal(1L);
        responseDto.setData(
                JwtResponceDto
                        .builder()
                        .token(token)
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .build()
        );
        return responseDto;
    }

    @Override
    public ResponseDto<UserDto> update(UserDto userDto, Long id) {
        return null;
    }

    @Override
    public ResponseDto<Page<UserDto>> getAllUser(Pageable pageable) {
        return null;
    }

    @Override
    public ResponseDto<UserDto> getById(Long id) {
        return null;
    }

    @Override
    public ResponseDto<String> deleteById(Long id) {
        return null;
    }

    @Override
    public boolean isExistUser(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    private boolean checkPassword(String password) {
        return password.length() >= 5 && password.length() <= 16;
    }
}
