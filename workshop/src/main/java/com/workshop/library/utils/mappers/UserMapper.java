package com.workshop.library.utils.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.workshop.library.api.dto.request.UserRequest;
import com.workshop.library.api.dto.response.UserOnlyLoans;
import com.workshop.library.api.dto.response.UserOnlyReservations;
import com.workshop.library.api.dto.response.UserResponse;
import com.workshop.library.api.dto.response.UserResponseFull;
import com.workshop.library.domain.entities.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    UserResponse entityToResponse(User user);

    UserResponseFull entityToResponseFull(User user);

    UserOnlyLoans entityToResponseLoans(User user);

    UserOnlyReservations entityToResponseReservations(User user);

    @Mapping(target = "loans", ignore = true)
    @Mapping(target = "reservations", ignore = true)
    User requestToEntity(UserRequest userRequest);

}
