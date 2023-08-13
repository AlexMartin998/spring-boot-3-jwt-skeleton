package com.skeleton.security.users.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserDto {
    private String firstname;
    private String lastname;
    private String fullName;
    private String email;
    private String password;

    // DB relations
    private Set<Long> roleIds;
}

