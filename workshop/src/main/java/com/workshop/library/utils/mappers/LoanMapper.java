package com.workshop.library.utils.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.workshop.library.api.dto.request.LoanRequest;
import com.workshop.library.api.dto.response.LoanResponse;
import com.workshop.library.domain.entities.Loan;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LoanMapper {

    @Mapping(target = "user.id" , source = "user_id")
    @Mapping(target = "book.id" , source = "book_id")
    Loan loanRequestToLoan(LoanRequest request);
    
    @Mapping(target = "user_id" , source = "user.id")
    @Mapping(target = "book_id" , source = "book.id")
    LoanResponse loanToloanResponse(Loan loan);
    
    @Mapping(target = "id", ignore = true)
    void updateFromloanRequest(LoanRequest request, @MappingTarget Loan loan);
    
}
