package com.workshop.library.api.dto.response;

import com.workshop.library.utils.enums.RoleType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    
    private Long id;

    private String userName;

    private String fullName;

    private String email;

    private RoleType role;

}
