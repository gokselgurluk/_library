package com.kutuphane.library.dto.request.book;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.kutuphane.library.dto.request.entity.Category;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookSaveRequest {

    @NotNull
    @JsonProperty("book_name")
    private String name;

    @NotNull
    private int publicationYear;

    private int stock;

    private int bookAuthorId;

    private List<Category> categoryList;

    private int bookPublisherId;

}
