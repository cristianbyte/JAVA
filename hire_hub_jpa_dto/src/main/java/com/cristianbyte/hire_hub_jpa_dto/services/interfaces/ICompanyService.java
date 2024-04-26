package com.cristianbyte.hire_hub_jpa_dto.services.interfaces;

import com.cristianbyte.hire_hub_jpa_dto.utils.dto.request.CompanyRequest;
import com.cristianbyte.hire_hub_jpa_dto.utils.dto.response.CompanyResponse;

public interface ICompanyService extends CrudService<CompanyRequest, CompanyResponse, String>{
    
}
