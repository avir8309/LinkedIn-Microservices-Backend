package com.example.LinkedIn.UserService.controllers;

import com.example.LinkedIn.UserService.dto.LoginRequestDto;
import com.example.LinkedIn.UserService.dto.SignUpRequestDto;
import com.example.LinkedIn.UserService.dto.UserDto;
import com.example.LinkedIn.UserService.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignUpRequestDto signUpRequestDto) {
        UserDto userDto = authService.signUp(signUpRequestDto);
        return ResponseEntity.ok(userDto);
    }
    @GetMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto) {
        String token = authService.login( loginRequestDto );
        return ResponseEntity.ok(token);
    }
}
