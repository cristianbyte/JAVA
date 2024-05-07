package com.cristianbyte.beautyhub.api.dto.response;

import java.time.LocalDateTime;

public class AppointmentToClient {
    private Long id;
    private LocalDateTime dateTime;
    private Integer duration;
    private String description;
    private ServiceResponse service;
    private EmployeeResponse employee;
}
