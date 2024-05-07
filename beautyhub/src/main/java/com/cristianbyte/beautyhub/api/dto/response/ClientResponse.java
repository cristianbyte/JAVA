package com.cristianbyte.beautyhub.api.dto.response;

import java.util.List;

import com.cristianbyte.beautyhub.domain.entity.Appointment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponse {
    private Long id;
    private String name;
    private String lastName;
    private String phone;
    private String email;
    private List<Appointment> appointments;
}
