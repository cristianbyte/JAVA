package com.cristianbyte.hire_hub_jpa_dto.utils.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder //Design patern for classes
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequest {

    @Size(min = 0, max = 40, message = "Name too long")
    @NotBlank(message = "The name's company is required")
    private String name;
    @NotBlank(message = "The location is required")
    private String location;
    @Size(min = 0,max = 15,message = "Contact too long")
    @NotBlank(message = "The contact is required")
    private String contact;
    
}
