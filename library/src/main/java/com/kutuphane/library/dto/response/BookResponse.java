package com.kutuphane.library.dto.response;


import com.kutuphane.library.dto.request.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {

    private int id;
    private String name;
    private int publicationYear;
    private int stock;
    private int bookCategoryId;
    private int bookPublisherId;
    private int bookAuthorId;
    private List<Category> categoryList;

}
