package com.brandenarms.dtos;

import com.brandenarms.models.ReadingStatus;
import jakarta.validation.constraints.NotBlank;

public class BookCreateDTO {
    @NotBlank(message = "Book must have a title.")
    private String title;

    @NotBlank(message = "Book must have an author.")
    private String author;

    public BookCreateDTO(String title, String author) {
        this.title = title;
        this.author = author;
    }
}
