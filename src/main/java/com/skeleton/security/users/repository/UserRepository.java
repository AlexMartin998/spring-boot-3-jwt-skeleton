package com.skeleton.security.users.repository;

import com.skeleton.security.users.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Usuario, Long> {
    // JpaRepository x default me da el Page para el  .findAll()

    public Optional<Usuario> findOneByEmail(String email);

    public Boolean existsByEmail(String email);

}