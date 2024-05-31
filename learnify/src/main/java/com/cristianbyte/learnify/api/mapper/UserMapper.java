package com.cristianbyte.learnify.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.cristianbyte.learnify.api.dto.request.UserRequest;
import com.cristianbyte.learnify.api.dto.response.UserResponse;
import com.cristianbyte.learnify.domain.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User userRequestToUser(UserRequest costRequest);

    UserResponse userToUserResponse(User user);

    void updateFromUserRequest(UserRequest request, @MappingTarget User user);
}