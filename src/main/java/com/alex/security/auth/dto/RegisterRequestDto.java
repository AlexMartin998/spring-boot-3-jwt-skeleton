package com.alex.security.auth.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.Data;


@Data
public class RegisterRequestDto {

    @NotEmpty
    private String firstname;

    @NotEmpty
    private String lastname;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&.])[A-Za-z\\d@$!%*?&.]{8,}$",
            message = "Password must be at least 8 characters long and contain at least one lowercase letter, one uppercase letter, one digit, and one special character."
    )
    private String password;

}
