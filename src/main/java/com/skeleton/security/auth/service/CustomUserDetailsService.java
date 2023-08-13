package com.skeleton.security.auth.service;

import com.skeleton.security.auth.jwt.UserDetailsImpl;
import com.skeleton.security.users.entity.Usuario;
import com.skeleton.security.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    // @Autowired in auto by constructor thanks to @RequiredArgsConstructor
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Todo: use userService to find user
        Usuario user = userRepository.findOneByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User not found with email: ".concat(email)));

        return new UserDetailsImpl(user);
    }

}
