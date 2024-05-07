package com.cristianbyte.beautyhub.api.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequest {
    @NotBlank(message = "date is required")
    private LocalDateTime dateTime;
    @Min(value = 10, message = "the duration less than 10 is invalid")
    @Max(value = 720, message = "the duration higher than 720 is invalid")
    private Integer duration;
    private String description;
    @NotNull(message = "The client id is required")
    private Long clientId;
    @NotNull(message = "The service id is required")
    private Long serviceId;
    @NotNull(message = "The employee id is required")
    private Long employeeId;
}
