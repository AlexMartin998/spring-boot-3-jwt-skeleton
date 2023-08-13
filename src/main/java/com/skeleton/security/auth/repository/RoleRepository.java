package com.skeleton.security.auth.repository;

import com.skeleton.security.auth.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {

    public Optional<Role> findOneByName(String name);

}
