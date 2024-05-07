package com.cristianbyte.beautyhub.api.dto.request;

import jakarta.validation.constraints.Email;
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
public class ClientRequest {
    @NotBlank(message = "Name is required.")
    private String name;
    @NotBlank(message = "Last name is required")
    private String lastName;
    @Size(min = 10, max = 20, message = "Phone is required.")
    private String phone;
    @Email(message = "Email not valid")
    @Size(min = 5, max = 100, message = "Invalid Email")
    private String email;

}
