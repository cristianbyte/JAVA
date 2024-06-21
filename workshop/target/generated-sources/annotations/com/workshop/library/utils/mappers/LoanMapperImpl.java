package com.workshop.library.utils.mappers;

import com.workshop.library.api.dto.request.LoanRequest;
import com.workshop.library.api.dto.response.LoanResponse;
import com.workshop.library.domain.entities.Book;
import com.workshop.library.domain.entities.Loan;
import com.workshop.library.domain.entities.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-21T13:34:51-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240524-2033, environment: Java 17.0.11 (Eclipse Adoptium)"
)
@Component
public class LoanMapperImpl implements LoanMapper {

    @Override
    public Loan loanRequestToLoan(LoanRequest request) {
        if ( request == null ) {
            return null;
        }

        Loan.LoanBuilder loan = Loan.builder();

        loan.user( loanRequestToUser( request ) );
        loan.book( loanRequestToBook( request ) );
        loan.loanDate( request.getLoanDate() );
        loan.returnDate( request.getReturnDate() );
        loan.status( request.getStatus() );

        return loan.build();
    }

    @Override
    public LoanResponse loanToloanResponse(Loan loan) {
        if ( loan == null ) {
            return null;
        }

        LoanResponse.LoanResponseBuilder loanResponse = LoanResponse.builder();

        loanResponse.user_id( loanUserId( loan ) );
        loanResponse.book_id( loanBookId( loan ) );
        loanResponse.loanDate( loan.getLoanDate() );
        loanResponse.returnDate( loan.getReturnDate() );
        loanResponse.status( loan.getStatus() );

        return loanResponse.build();
    }

    @Override
    public void updateFromloanRequest(LoanRequest request, Loan loan) {
        if ( request == null ) {
            return;
        }

        loan.setLoanDate( request.getLoanDate() );
        loan.setReturnDate( request.getReturnDate() );
        loan.setStatus( request.getStatus() );
    }

    protected User loanRequestToUser(LoanRequest loanRequest) {
        if ( loanRequest == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( loanRequest.getUser_id() );

        return user.build();
    }

    protected Book loanRequestToBook(LoanRequest loanRequest) {
        if ( loanRequest == null ) {
            return null;
        }

        Book.BookBuilder book = Book.builder();

        book.id( loanRequest.getBook_id() );

        return book.build();
    }

    private Long loanUserId(Loan loan) {
        if ( loan == null ) {
            return null;
        }
        User user = loan.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long loanBookId(Loan loan) {
        if ( loan == null ) {
            return null;
        }
        Book book = loan.getBook();
        if ( book == null ) {
            return null;
        }
        Long id = book.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
