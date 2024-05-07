package com.cristianbyte.beautyhub.api.dto.response;

import java.time.LocalDateTime;


public class AppointmentResponse {
    private Long id;
    private LocalDateTime dateTime;
    private Integer duration;
    private String description;
    private BasicClientResponse client;
    private ServiceResponse service;
    private EmployeeResponse employee;

}
