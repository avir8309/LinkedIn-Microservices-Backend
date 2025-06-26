package com.example.LinkedIn.UserService.service;

import com.example.LinkedIn.UserService.Utils.PasswordUtil;
import com.example.LinkedIn.UserService.dto.LoginRequestDto;
import com.example.LinkedIn.UserService.dto.SignUpRequestDto;
import com.example.LinkedIn.UserService.dto.UserDto;
import com.example.LinkedIn.UserService.entity.User;
import com.example.LinkedIn.UserService.exceptions.BadRequestException;
import com.example.LinkedIn.UserService.exceptions.ResourceNotFoundException;
import com.example.LinkedIn.UserService.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;

    public UserDto signUp(SignUpRequestDto signUpRequestDto) {
        User user = modelMapper.map(signUpRequestDto, User.class);
        user.setPassword(PasswordUtil.hashPassword((signUpRequestDto.getPassword())));
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDto.class);
    }

    public String login(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + loginRequestDto.getEmail()));
        boolean isPasswordCorrect = PasswordUtil.matchPassword(loginRequestDto.getPassword(), user.getPassword());
        if (!isPasswordCorrect) {
            throw new BadRequestException("Incorrect Password");
        }
        return jwtService.generateAccessToken( user );
    }
}


