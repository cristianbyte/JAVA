package com.cristianbyte.hire_hub_jpa_dto.utils.dto.response;

import com.cristianbyte.hire_hub_jpa_dto.entities.Company;
import com.cristianbyte.hire_hub_jpa_dto.utils.enums.StatusVacant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacantResponse {
    private Long id;
    private String title;
    private String description;
    private StatusVacant status;
    private CompanyToVacantResponse company;

}
