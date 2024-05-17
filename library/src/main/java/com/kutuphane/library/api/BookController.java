package com.kutuphane.library.api;

import com.kutuphane.library.business.abstracts.IAuthorService;
import com.kutuphane.library.business.abstracts.IBookService;
import com.kutuphane.library.business.abstracts.IPublisherService;
import com.kutuphane.library.core.config.modelMapper.IModelMapperService;
import com.kutuphane.library.core.result.Result;
import com.kutuphane.library.core.result.ResultData;
import com.kutuphane.library.core.utilies.ResultHelper;
import com.kutuphane.library.dto.request.book.BookSaveRequest;
import com.kutuphane.library.dto.request.book.BookUpdateRequest;
import com.kutuphane.library.dto.response.AuthorResponse;
import com.kutuphane.library.dto.response.BookResponse;
import com.kutuphane.library.dto.response.CursorResponse;
import com.kutuphane.library.dto.request.entity.Author;
import com.kutuphane.library.dto.request.entity.Book;
import com.kutuphane.library.dto.request.entity.Publisher;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/book")
@RequiredArgsConstructor
public class BookController {
    private final IBookService bookService;
    private final IModelMapperService modelMapper;
    private final IAuthorService authorService;
    private final IPublisherService publisherService;


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BookResponse> save(@Valid @RequestBody BookSaveRequest bookSaveRequest) {
        Book saveBook = this.modelMapper.forRequest().map(bookSaveRequest, Book.class);

        Author author = this.authorService.get(bookSaveRequest.getBookAuthorId());
        saveBook.setAuthor(author);

        Publisher publisher = this.publisherService.get(bookSaveRequest.getBookPublisherId());
        saveBook.setPublisher(publisher);

        this.bookService.save(saveBook);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveBook, BookResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus
    public ResultData<BookResponse> get(@PathVariable("id") int id) {
        return ResultHelper.success(this.modelMapper.forResponse().map(this.bookService.get(id), BookResponse.class));
    }

    @GetMapping("/{id}/author")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AuthorResponse> getAuthor(@PathVariable("id") int id) {
        return ResultHelper.success(this.modelMapper.forResponse().map(this.bookService.get(id).getAuthor(), AuthorResponse.class));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<BookResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize
    ){
        Page<Book> bookPage = this.bookService.cursor(page,pageSize);
        Page<BookResponse> bookResponsePage = bookPage.map(book -> this.modelMapper.forResponse().map(book, BookResponse.class));

        return ResultHelper.cursor(bookResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BookResponse> update(@Valid @RequestBody BookUpdateRequest bookUpdateRequest) {
        Book updeteBook = this.modelMapper.forRequest().map(bookUpdateRequest, Book.class);
        this.bookService.update(updeteBook);
        return ResultHelper.success(this.modelMapper.forResponse().map(updeteBook, BookResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.bookService.delete(id);
        return ResultHelper.successResult();
    }
}

