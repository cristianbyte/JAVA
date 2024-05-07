package com.cristianbyte.beautyhub.infraestructure.abstract_service;

import java.util.List;

import com.cristianbyte.beautyhub.api.dto.request.ServiceRequest;
import com.cristianbyte.beautyhub.api.dto.response.ServiceResponse;

public interface IServiceService extends CRUDService<ServiceRequest ,ServiceResponse, Long> {

    public List<ServiceResponse> search(String name);

    public final String FILED_BY_SORT = "price";
}
