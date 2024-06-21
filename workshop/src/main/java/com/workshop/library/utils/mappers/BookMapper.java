package com.workshop.library.utils.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.workshop.library.api.dto.request.BookRequest;
import com.workshop.library.api.dto.response.BookResponse;
import com.workshop.library.api.dto.response.BookResponseFull;
import com.workshop.library.domain.entities.Book;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {
    
    @Mapping(target = "loans", ignore = true)
    @Mapping(target = "reservations", ignore = true)
    Book RequestToBook(BookRequest bookRequest);

    BookResponse BookToResponse(Book book);

    BookResponseFull BookToResponseFull(Book book);

}