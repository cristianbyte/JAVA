package com.workshop.library.infrastructure.abstract_services;

import com.workshop.library.api.dto.request.UserRequest;
import com.workshop.library.api.dto.response.UserResponseFull;

public interface IUserService extends CrudAbstractService<UserRequest, UserResponseFull, Long>{
    
}
