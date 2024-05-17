package com.kutuphane.library.business.concretes;

import com.kutuphane.library.business.abstracts.IBookBorrowingService;
import com.kutuphane.library.core.exeption.NotFoundException;
import com.kutuphane.library.core.utilies.Msg;
import com.kutuphane.library.dao.BookBorrowingRepository;
import com.kutuphane.library.dto.request.entity.BookBorrowing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookBorrowingManager implements IBookBorrowingService {

    private final BookBorrowingRepository bookBorrowingRepo;

    public BookBorrowingManager(BookBorrowingRepository bookBorrowingRepo) {
        this.bookBorrowingRepo=bookBorrowingRepo;
    }

    @Override
    public BookBorrowing save(BookBorrowing bookBorrowing) {
        return this.bookBorrowingRepo.save(bookBorrowing);
    }

    @Override
    public BookBorrowing get(int id) {
        return this.bookBorrowingRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<BookBorrowing> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.bookBorrowingRepo.findAll(pageable);
    }

    @Override
    public BookBorrowing update(BookBorrowing bookBorrowing) {
        this.get(bookBorrowing.getId());
        return this.bookBorrowingRepo.save(bookBorrowing);
    }

    @Override
    public boolean delete(int id) {
        BookBorrowing bookBorrowing = this.get(id);
        this.bookBorrowingRepo.delete(bookBorrowing);
        return true;
    }
}
