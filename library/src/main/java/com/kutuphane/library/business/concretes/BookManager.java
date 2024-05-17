package com.kutuphane.library.business.concretes;

import com.kutuphane.library.business.abstracts.IBookService;
import com.kutuphane.library.core.exeption.NotFoundException;
import com.kutuphane.library.core.utilies.Msg;
import com.kutuphane.library.dao.BookRepository;
import com.kutuphane.library.dto.request.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookManager implements IBookService {

    private final BookRepository bookRepo;

    public BookManager(BookRepository bookRepo) {
        this.bookRepo=bookRepo;
    }

    @Override
    public Book save(Book book) {
        return this.bookRepo.save(book);
    }

    @Override
    public Book get(int id) {
        return this.bookRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Book> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.bookRepo.findAll(pageable);
    }

    @Override
    public Book update(Book book) {
        this.get(book.getId());
        return this.bookRepo.save(book);
    }

    @Override
    public boolean delete(int id) {
        Book book = this.get(id);
        this.bookRepo.delete(book);
        return true;
    }
}
