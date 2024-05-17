package com.kutuphane.library.business.abstracts;

import com.kutuphane.library.dto.request.entity.Book;
import org.springframework.data.domain.Page;

public interface IBookService {
    Book save(Book book);
    Book get(int id);
    Page<Book> cursor (int page, int pageSize);

    Book update(Book book);

    boolean delete(int id);

}
