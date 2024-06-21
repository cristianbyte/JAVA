package com.workshop.library.infrastructure.abstract_services;

import com.workshop.library.api.dto.request.LoanRequest;
import com.workshop.library.api.dto.response.LoanResponse;

public interface ILoanService extends CrudAbstractService<LoanRequest, LoanResponse, Long> {

    
}