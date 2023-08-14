package com.skeleton.security.auth.service;

import com.skeleton.security.auth.entity.Role;

import java.util.Optional;


public interface RoleService {

    Optional<Role> findOneByName(String name);

}
