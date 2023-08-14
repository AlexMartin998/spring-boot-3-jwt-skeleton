package com.skeleton.security.auth.service;

import com.skeleton.security.auth.dto.AuthResponseDto;
import com.skeleton.security.auth.dto.LoginDto;
import com.skeleton.security.auth.dto.LoginResponseDto;
import com.skeleton.security.auth.dto.RegisterRequestDto;


public interface AuthService {

    AuthResponseDto register(RegisterRequestDto request);

    LoginResponseDto login(LoginDto request);
}
