package com.cristianbyte.beautyhub.api.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponse {
    private Long id;
    private LocalDateTime dateTime;
    private Integer duration;
    private String description;
    private BasicClientResponse client;
    private ServiceResponse service;
    private EmployeeResponse employee;

}
