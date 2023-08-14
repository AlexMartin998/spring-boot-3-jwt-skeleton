package com.skeleton.security.auth.service;

import com.skeleton.security.auth.entity.Role;
import com.skeleton.security.auth.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;


    @Override
    public Optional<Role> findOneByName(String name) {
        return roleRepository.findOneByName(name);
    }

}
