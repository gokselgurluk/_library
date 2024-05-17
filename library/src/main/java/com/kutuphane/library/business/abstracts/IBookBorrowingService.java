package com.kutuphane.library.business.abstracts;

import com.kutuphane.library.dto.request.entity.BookBorrowing;
import org.springframework.data.domain.Page;

public interface IBookBorrowingService {
    BookBorrowing save(BookBorrowing bookBorrowing);
    BookBorrowing get(int id);
    Page<BookBorrowing> cursor (int page, int pageSize);

    BookBorrowing update(BookBorrowing bookBorrowing);

    boolean delete(int id);

}
