package com.workshop.library.utils.mappers;

import com.workshop.library.api.dto.request.ReservationRequest;
import com.workshop.library.api.dto.response.BookResponse;
import com.workshop.library.api.dto.response.ReservationResponse;
import com.workshop.library.api.dto.response.ReservationResponseFull;
import com.workshop.library.api.dto.response.UserResponse;
import com.workshop.library.domain.entities.Book;
import com.workshop.library.domain.entities.Reservation;
import com.workshop.library.domain.entities.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-21T13:34:51-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240524-2033, environment: Java 17.0.11 (Eclipse Adoptium)"
)
@Component
public class ReservationMapperImpl implements ReservationMapper {

    @Override
    public void updateFromReservationRequest(ReservationRequest request, Reservation reservation) {
        if ( request == null ) {
            return;
        }

        reservation.setStatus( request.getStatus() );
    }

    @Override
    public Reservation reservationRequestToReservation(ReservationRequest request) {
        if ( request == null ) {
            return null;
        }

        Reservation.ReservationBuilder reservation = Reservation.builder();

        reservation.user( reservationRequestToUser( request ) );
        reservation.book( reservationRequestToBook( request ) );
        reservation.status( request.getStatus() );

        return reservation.build();
    }

    @Override
    public ReservationResponse reservationToReservationResponse(Reservation reservation) {
        if ( reservation == null ) {
            return null;
        }

        ReservationResponse.ReservationResponseBuilder reservationResponse = ReservationResponse.builder();

        reservationResponse.book( bookToBookResponse( reservation.getBook() ) );
        reservationResponse.id( reservation.getId() );
        reservationResponse.reservationDate( reservation.getReservationDate() );
        reservationResponse.status( reservation.getStatus() );

        return reservationResponse.build();
    }

    @Override
    public ReservationResponseFull reservationToReservationResponseFull(Reservation reservation) {
        if ( reservation == null ) {
            return null;
        }

        ReservationResponseFull.ReservationResponseFullBuilder reservationResponseFull = ReservationResponseFull.builder();

        reservationResponseFull.book( bookToBookResponse( reservation.getBook() ) );
        reservationResponseFull.id( reservation.getId() );
        reservationResponseFull.reservationDate( reservation.getReservationDate() );
        reservationResponseFull.status( reservation.getStatus() );
        reservationResponseFull.user( userToUserResponse( reservation.getUser() ) );

        return reservationResponseFull.build();
    }

    protected User reservationRequestToUser(ReservationRequest reservationRequest) {
        if ( reservationRequest == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.id( reservationRequest.getUserId() );

        return user.build();
    }

    protected Book reservationRequestToBook(ReservationRequest reservationRequest) {
        if ( reservationRequest == null ) {
            return null;
        }

        Book.BookBuilder book = Book.builder();

        book.id( reservationRequest.getBookId() );

        return book.build();
    }

    protected BookResponse bookToBookResponse(Book book) {
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

    protected UserResponse userToUserResponse(User user) {
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
}
