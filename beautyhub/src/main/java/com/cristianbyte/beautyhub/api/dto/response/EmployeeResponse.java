package com.cristianbyte.beautyhub.api.dto.response;

import javax.management.relation.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
    private int id;
    private String name;
    private String lastName;
    private String phone;
    private Role role;
}
