package com.workshop.library.api.dto.response;

import java.time.LocalDateTime;

import com.workshop.library.utils.enums.StatusType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponseFull {
    
    private Long id;
    
    private LocalDateTime reservationDate;

    private StatusType status;

    private UserResponse user;

    private BookResponse book;

}
