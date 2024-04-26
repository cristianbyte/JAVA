package com.cristianbyte.hire_hub_jpa_dto.utils.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder //Design patern for classes
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequest {
    private String name;
    private String location;
    private String contact;
    
}
