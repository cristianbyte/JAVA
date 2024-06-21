package com.workshop.library.utils.mappers;

import com.workshop.library.api.dto.request.UserRequest;
import com.workshop.library.api.dto.response.LoanResponse;
import com.workshop.library.api.dto.response.UserResponse;
import com.workshop.library.api.dto.response.UserResponseFull;
import com.workshop.library.domain.entities.Loan;
import com.workshop.library.domain.entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-21T12:51:57-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240524-2033, environment: Java 17.0.11 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponse entityToResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.email( user.getEmail() );
        userResponse.fullName( user.getFullName() );
        userResponse.id( user.getId() );
        userResponse.role( user.getRole() );
        userResponse.userName( user.getUserName() );

        return userResponse.build();
    }

    @Override
    public UserResponseFull entityToResponseFull(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseFull.UserResponseFullBuilder userResponseFull = UserResponseFull.builder();

        userResponseFull.email( user.getEmail() );
        userResponseFull.fullName( user.getFullName() );
        userResponseFull.loans( loanListToLoanResponseList( user.getLoans() ) );
        userResponseFull.role( user.getRole() );
        userResponseFull.userName( user.getUserName() );

        return userResponseFull.build();
    }

    @Override
    public User requestToEntity(UserRequest userRequest) {
        if ( userRequest == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.email( userRequest.getEmail() );
        user.fullName( userRequest.getFullName() );
        user.password( userRequest.getPassword() );
        user.role( userRequest.getRole() );
        user.userName( userRequest.getUserName() );

        return user.build();
    }

    protected LoanResponse loanToLoanResponse(Loan loan) {
        if ( loan == null ) {
            return null;
        }

        LoanResponse.LoanResponseBuilder loanResponse = LoanResponse.builder();

        loanResponse.loanDate( loan.getLoanDate() );
        loanResponse.returnDate( loan.getReturnDate() );
        loanResponse.status( loan.getStatus() );

        return loanResponse.build();
    }

    protected List<LoanResponse> loanListToLoanResponseList(List<Loan> list) {
        if ( list == null ) {
            return null;
        }

        List<LoanResponse> list1 = new ArrayList<LoanResponse>( list.size() );
        for ( Loan loan : list ) {
            list1.add( loanToLoanResponse( loan ) );
        }

        return list1;
    }
}
