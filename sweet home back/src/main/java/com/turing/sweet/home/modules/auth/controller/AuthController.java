package com.turing.sweet.home.modules.auth.controller;

import com.turing.sweet.home.modules.auth.dto.LoginReqDto;
import com.turing.sweet.home.modules.auth.dto.LoginResDto;
import com.turing.sweet.home.modules.auth.dto.RegisterReqDto;
import com.turing.sweet.home.modules.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterReqDto registerReqDto) {
        this.authService.register(registerReqDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResDto> login(@RequestBody LoginReqDto loginReqDto) {
        LoginResDto loginResDto = this.authService.login(loginReqDto);
        return ResponseEntity.ok(loginResDto);
    }
}
