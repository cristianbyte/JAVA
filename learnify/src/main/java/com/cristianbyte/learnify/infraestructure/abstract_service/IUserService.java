package com.cristianbyte.learnify.infraestructure.abstract_service;


import com.cristianbyte.learnify.api.dto.request.UserRequest;
import com.cristianbyte.learnify.api.dto.response.UserResponse;

public interface IUserService extends CrudAbstractService<UserRequest, UserResponse, String>{
}
