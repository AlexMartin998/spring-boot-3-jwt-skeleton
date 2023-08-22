package com.skeleton.security.auth.service;

import com.skeleton.security.auth.dto.AuthResponseDto;
import com.skeleton.security.auth.dto.LoginDto;
import com.skeleton.security.auth.dto.LoginResponseDto;
import com.skeleton.security.auth.dto.RegisterRequestDto;
import com.skeleton.security.users.entity.Usuario;
import com.skeleton.security.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    // Auto Inject by Constructor thanks to @RequiredArgsConstructor (FINAL properties)
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final CustomUserDetailsService customUserDetailsService;

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public AuthResponseDto register(RegisterRequestDto request) {
        Usuario user = mapToEntity(request);

        Usuario newUser = userService.create(user);

        return mapToDto(newUser);
    }

    @Override
    public LoginResponseDto login(LoginDto request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var user = customUserDetailsService.loadUserByUsername(request.getEmail());
        var jwtToken = jwtService.generateJwt(user);

        return LoginResponseDto.builder().token(jwtToken).build();
    }


    // DTO to entity
    private Usuario mapToEntity(RegisterRequestDto registerDTO) {
        Usuario newUser = modelMapper.map(registerDTO, Usuario.class);
        newUser.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        return newUser;
    }

    // entity to DTO
    private AuthResponseDto mapToDto(Usuario user) {
        return modelMapper.map(user, AuthResponseDto.class);
    }
}
