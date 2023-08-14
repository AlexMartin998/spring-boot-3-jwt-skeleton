package com.skeleton.security.users.service;

import com.skeleton.security.auth.entity.Role;
import com.skeleton.security.auth.service.RoleService;
import com.skeleton.security.common.constants.RoleType;
import com.skeleton.security.users.entity.Usuario;
import com.skeleton.security.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    // Auto Inject by Constructor thanks to @RequiredArgsConstructor (FINAL properties)
    private final RoleService roleService;
    private final UserRepository userRepository;


    @Override
    public Usuario save(Usuario user) {
        Role role = roleService.findOneByName(RoleType.USER_ROLE.name()).orElseThrow();
        user.setRoles(Collections.singleton(role));  // Collections.singleton()  --> return  Set<T>

        return userRepository.save(user);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

}
