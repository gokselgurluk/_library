package com.kutuphane.library.business.abstracts;

import com.kutuphane.library.dto.request.entity.Author;
import org.springframework.data.domain.Page;

public interface IAuthorService {
    Author save(Author author);
    Author get(int id);
    Page<Author> cursor (int page, int pageSize);

    Author update(Author author);

    boolean delete(int id);

}

