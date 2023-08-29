package com.alex.security.auth.jwt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

    private final SecurityErrorResponse securityErrorResponse;


    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException, ServletException {
        securityErrorResponse.sendErrorResponse(
                request,
                response,
                HttpServletResponse.SC_UNAUTHORIZED,
                authException.getMessage()
        );
    }

}
