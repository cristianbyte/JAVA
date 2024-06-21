package com.workshop.library.infrastructure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.workshop.library.api.dto.request.BookRequest;
import com.workshop.library.api.dto.response.BookResponse;
import com.workshop.library.api.dto.response.BookResponseFull;
import com.workshop.library.domain.entities.Book;
import com.workshop.library.domain.repositories.BookRepository;
import com.workshop.library.infrastructure.abstract_services.IBookService;
import com.workshop.library.utils.enums.exceptions.BadRequestException;
import com.workshop.library.utils.enums.message.ErrorMessage;
import com.workshop.library.utils.mappers.BookMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BookService implements IBookService {

    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    private final BookMapper bookMapper;

    @Override
    public BookResponse getById(Long id) {
        Book book = this.find(id);
        return this.bookMapper.BookToResponse(book);
    }

    @Override
    public BookResponse create(BookRequest request) {
        Book book = this.bookMapper.RequestToBook(request);
        return this.bookMapper.BookToResponse(this.bookRepository.save(book));
    }

    @Override
    public BookResponse update(Long id, BookRequest request) {

        this.find(id);
        Book bookToUpdate = this.bookMapper.RequestToBook(request);
        bookToUpdate.setId(id);
        Book bookSaved = this.bookRepository.save(bookToUpdate);
        return this.bookMapper.BookToResponse(bookSaved);

    }

    @Override
    public void delete(Long id) {
        this.bookRepository.deleteById(id);
    }

    private Book find(Long id) {
        return this.bookRepository.findById(id).orElseThrow(() -> new BadRequestException(ErrorMessage.idNotFound("book")));
    }

    @Override
    public Page<BookResponse> getAll(int page, int size) {

        if (page < 0)
            page = 0;

        PageRequest pagination = PageRequest.of(page, size);

        return this.bookRepository.findAll(pagination).map(bookMapper::BookToResponse);

    }

    @Override
    public BookResponseFull getBookLoans(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBookLoans'");
    }

}
