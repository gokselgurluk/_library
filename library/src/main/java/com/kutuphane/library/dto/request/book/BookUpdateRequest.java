package com.kutuphane.library.dto.request.book;


import com.kutuphane.library.dto.request.entity.Category;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookUpdateRequest {
    @Positive(message = "ID değeri pozitif sayı olmak zorunda")
    private int id;

    @NotNull(message = "Kategori ismi boş veya null olamaz")
    private String name;

    @NotNull
    private int publicationYear;

    private int stock;

    private int bookAuthorId;

    private List<Category> categoryList;

    private int bookPublisherId;
}