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
public class EmployeeRequest {
    @NotBlank(message = "Name is required")
    private String firstName;
    @NotBlank(message = "LastName is required")
    private String lastName;
    @Size(min = 10, max = 20, message = "phone between 10 and 20")
    private String phone;
    @Email(message = "Email invalid")
    private String email;

}
