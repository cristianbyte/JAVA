package com.cristianbyte.learnify.api.dto.request;

import com.cristianbyte.learnify.util.enums.RoleType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {    
    @NotNull(message = "username is required")
    private String username;
    @NotNull(message = "password is required")
    private String password;
    @NotNull(message = "fullname is required")
    private String fullName;
    @Email(message = "email is required")
    private String email;
    @NotNull(message = "role is required")
    private RoleType role;
}
