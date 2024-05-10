package com.riwi.beautySalon.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "Username is required")
    @Size(min=3, max=150,message="Username Between 3 and 150 characters")
    private String userName;
    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 150, message = "Password must be between 6 and 150 characters")
    //@Pattern(regexp = "regular expresion")
    private String password;
}
