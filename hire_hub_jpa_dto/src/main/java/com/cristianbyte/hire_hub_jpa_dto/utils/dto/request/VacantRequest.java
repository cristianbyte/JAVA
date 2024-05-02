package com.cristianbyte.hire_hub_jpa_dto.utils.dto.request;
import com.cristianbyte.hire_hub_jpa_dto.utils.enums.StatusVacant;

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
public class VacantRequest {
    @Size(min=0,max=255)
    @NotBlank(message = "Title required")
    private String title;
    @NotBlank(message = "description required")
    private String description;
    private StatusVacant status;
    @Size(min = 0, max = 32)
    @NotBlank(message = "Company's id is required")
    private String companyId;

}
