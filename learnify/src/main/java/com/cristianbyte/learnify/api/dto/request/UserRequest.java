package com.cristianbyte.learnify.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {    
    @NotBlank(message = "username is required")
    private String username;

    @NotBlank(message = "password is required")
    private String password;

    @NotBlank(message = "fullname is required")
    private String fullName;

    @NotBlank(message = "email is required")
    @Email(message = "invalid email format")
    private String email;
    @Pattern(regexp = "ADMIN|STUDENT|TEACHER|GUEST", message = "The state must be ADMIN, STUDENT, TEACHER or GUEST")
    private String role;
}