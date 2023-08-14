package com.skeleton.security.auth.service;

import com.skeleton.security.auth.jwt.UserDetailsImpl;
import com.skeleton.security.users.entity.Usuario;
import com.skeleton.security.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    // @Autowired in auto by constructor thanks to @RequiredArgsConstructor
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Usuario user = userService.findOneByEmail(email);

        return new UserDetailsImpl(user);
    }

}
