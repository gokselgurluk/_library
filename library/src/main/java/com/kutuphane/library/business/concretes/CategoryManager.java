package com.kutuphane.library.business.concretes;

import com.kutuphane.library.business.abstracts.ICategoryService;
import com.kutuphane.library.core.exeption.NotFoundException;
import com.kutuphane.library.core.utilies.Msg;
import com.kutuphane.library.dao.CategoryRepository;
import com.kutuphane.library.dto.request.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryManager implements ICategoryService {

    private final CategoryRepository categoryRepo;

    public CategoryManager(CategoryRepository categoryRepo) {
        this.categoryRepo=categoryRepo;
    }

    @Override
    public Category save(Category category) {
        return this.categoryRepo.save(category);
    }

    @Override
    public Category get(int id) {
        return this.categoryRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Category> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.categoryRepo.findAll(pageable);
    }

    @Override
    public Category update(Category category) {
        this.get(category.getId());
        return this.categoryRepo.save(category);
    }

    @Override
    public boolean delete(int id) {
        Category category = this.get(id);
        this.categoryRepo.delete(category);
        return true;
    }
}
