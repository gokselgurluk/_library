package com.kutuphane.library.business.abstracts;

import com.kutuphane.library.dto.request.entity.Category;
import org.springframework.data.domain.Page;

public interface ICategoryService {
    Category save(Category category);
    Category get(int id);
    Page<Category> cursor (int page, int pageSize);

    Category update(Category category);

    boolean delete(int id);

}

