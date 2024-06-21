package com.workshop.library.utils.mappers;

import com.workshop.library.api.dto.request.BookRequest;
import com.workshop.library.api.dto.response.BookResponse;
import com.workshop.library.api.dto.response.BookResponseFull;
import com.workshop.library.api.dto.response.LoanResponse;
import com.workshop.library.domain.entities.Book;
import com.workshop.library.domain.entities.Loan;
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
public class BookMapperImpl implements BookMapper {

    @Override
    public Book RequestToBook(BookRequest bookRequest) {
        if ( bookRequest == null ) {
            return null;
        }

        Book.BookBuilder book = Book.builder();

        book.author( bookRequest.getAuthor() );
        book.genre( bookRequest.getGenre() );
        book.id( bookRequest.getId() );
        book.isbn( bookRequest.getIsbn() );
        book.publicationYear( bookRequest.getPublicationYear() );
        book.title( bookRequest.getTitle() );

        return book.build();
    }

    @Override
    public BookResponse BookToResponse(Book book) {
        if ( book == null ) {
            return null;
        }

        BookResponse.BookResponseBuilder bookResponse = BookResponse.builder();

        bookResponse.author( book.getAuthor() );
        bookResponse.genre( book.getGenre() );
        bookResponse.isbn( book.getIsbn() );
        bookResponse.publicationYear( book.getPublicationYear() );
        bookResponse.title( book.getTitle() );

        return bookResponse.build();
    }

    @Override
    public BookResponseFull BookToResponseFull(Book book) {
        if ( book == null ) {
            return null;
        }

        BookResponseFull.BookResponseFullBuilder bookResponseFull = BookResponseFull.builder();

        bookResponseFull.author( book.getAuthor() );
        bookResponseFull.genre( book.getGenre() );
        bookResponseFull.isbn( book.getIsbn() );
        bookResponseFull.loans( loanListToLoanResponseList( book.getLoans() ) );
        bookResponseFull.publicationYear( book.getPublicationYear() );
        bookResponseFull.title( book.getTitle() );

        return bookResponseFull.build();
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
