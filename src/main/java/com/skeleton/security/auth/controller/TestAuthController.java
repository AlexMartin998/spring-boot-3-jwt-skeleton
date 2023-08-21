package com.skeleton.security.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
//@RequestMapping("/api/v1/free")
@RequiredArgsConstructor
public class TestAuthController {

    private static final String ADMIN_ROLE = "ADMIN_ROLE";


    @GetMapping("/api/v1/free/user")
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok("Hello from Free endpoint - User");
    }

    @GetMapping("/api/v1/protected")
    public ResponseEntity<String> defaultProtected(Authentication authentication) {
        String authUserEmail = ((UserDetails) authentication.getPrincipal()).getUsername();

        return ResponseEntity.ok("Hello from Protected endpoint - User | Authentication | ".concat(authUserEmail));
    }

    @GetMapping("/api/v1/protected/admin")
    @Secured(ADMIN_ROLE)
    public ResponseEntity<String> sayHelloAdmin() {
        return ResponseEntity.ok("Hello from secured endpoint - Admin");
    }

}
