package com.workshop.library.infrastructure.abstract_services;

import org.springframework.data.domain.Page;

import com.workshop.library.api.dto.request.BookRequest;
import com.workshop.library.api.dto.response.BookResponse;
import com.workshop.library.api.dto.response.BookResponseFull;

public interface IBookService extends CrudAbstractService<BookRequest, BookResponse, Long> {

    public Page<BookResponse> getAll(int page, int size);
    public BookResponseFull getBookLoans(Long id);
    
}
