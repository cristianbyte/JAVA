package com.workshop.library.infrastructure.abstract_services;

import com.workshop.library.api.dto.request.ReservationRequest;
import com.workshop.library.api.dto.response.ReservationResponseFull;

public interface IReservationService extends CrudAbstractService<ReservationRequest, ReservationResponseFull, Long> {
    
}
