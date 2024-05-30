package com.cristianbyte.learnify.api.mapper;

import org.mapstruct.Mapper;

import com.cristianbyte.learnify.api.dto.request.UserRequest;
import com.cristianbyte.learnify.api.dto.response.UserResponse;
import com.cristianbyte.learnify.domain.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<UserRequest, UserResponse, User>{
    UserResponse userToUserResponse(User user);
}
