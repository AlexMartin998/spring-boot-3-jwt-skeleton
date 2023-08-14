package com.skeleton.security.users.service;

import com.skeleton.security.users.entity.Usuario;


// no va @Service ya q al inyectarlo Spring Boot usara la 1ra Implementacion q encuentre
public interface UserService {

    Usuario save(Usuario user);

    boolean existsByEmail(String email);

}
