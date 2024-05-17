package com.kutuphane.library.business.concretes;

import com.kutuphane.library.business.abstracts.IAuthorService;
import com.kutuphane.library.core.exeption.NotFoundException;
import com.kutuphane.library.core.utilies.Msg;
import com.kutuphane.library.dao.AuthorRepository;
import com.kutuphane.library.dto.request.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AuthorManager implements IAuthorService {

    private final AuthorRepository authorRepo;

    public AuthorManager(AuthorRepository authorRepo) {
        this.authorRepo=authorRepo;
    }

    @Override
    public Author save(Author author) {
        return this.authorRepo.save(author);
    }

    @Override
    public Author get(int id) {
        return this.authorRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Author> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.authorRepo.findAll(pageable);
    }

    @Override
    public Author update(Author author) {
        this.get(author.getId());
        return this.authorRepo.save(author);
    }

    @Override
    public boolean delete(int id) {
        Author author = this.get(id);
        this.authorRepo.delete(author);
        return true;
    }
}