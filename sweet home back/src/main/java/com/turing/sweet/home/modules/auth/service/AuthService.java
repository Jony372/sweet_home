package com.turing.sweet.home.modules.auth.service;

import com.turing.sweet.home.config.JwtService;
import com.turing.sweet.home.exceptions.BadRequestException;
import com.turing.sweet.home.exceptions.ResourceNotFoundException;
import com.turing.sweet.home.modules.auth.dto.LoginReqDto;
import com.turing.sweet.home.modules.auth.dto.LoginResDto;
import com.turing.sweet.home.modules.auth.dto.RegisterReqDto;
import com.turing.sweet.home.modules.users.entity.UserEntity;
import com.turing.sweet.home.modules.users.mapper.UserMapper;
import com.turing.sweet.home.modules.users.repository.UserRepository;
import com.turing.sweet.home.modules.users.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(JwtService jwtService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(RegisterReqDto registerReqDto) {
        if(this.userRepository.existsByUsername(registerReqDto.username())) {
            throw new BadRequestException("El nombre de usuario ya está en uso");
        }
        if(!registerReqDto.email().isEmpty() && this.userRepository.existsByEmail(registerReqDto.email())) {
            throw new BadRequestException("El correo electrónico ya está en uso");
        }
        if(!registerReqDto.phone().isEmpty() && this.userRepository.existsByPhone(registerReqDto.phone())) {
            throw new BadRequestException("El teléfono ya está en uso");
        }
        UserEntity user = new UserEntity(
                registerReqDto.name(),
                registerReqDto.lastName(),
                registerReqDto.username(),
                registerReqDto.email(),
                registerReqDto.phone()
        );
        user.setPassword(passwordEncoder.encode(registerReqDto.password()));
        this.userRepository.save(user);
    }

    public LoginResDto login(LoginReqDto loginReqDto) {
        UserEntity user = this.userRepository.findByUsername(loginReqDto.username())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un usuario con el nombre de usuario " + loginReqDto.username()));
        if(!passwordEncoder.matches(loginReqDto.password(), user.getPassword())) {
            throw new BadRequestException("Contraseña incorrecta");
        }
        String token = jwtService.generateToken(user, loginReqDto.rememberMe());
        return new LoginResDto(token, UserMapper.toUserProfileDto(user));
    }
}
