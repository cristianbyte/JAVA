package com.cristianbyte.beautyhub.api.dto.request;

import java.math.BigDecimal;

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
public class ServiceRequest {
    @NotNull
    @NotBlank(message = "name is required.")
    private String name;
    private String description;
    private BigDecimal price;
}
