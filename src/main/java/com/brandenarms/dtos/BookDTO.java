package com.brandenarms.dtos;

import com.brandenarms.models.ReadingStatus;
import jakarta.validation.constraints.NotBlank;

public class BookDTO {
    @NotBlank
    private final Long id;

    @NotBlank
    private final String title;

    @NotBlank
    private final String author;

    public BookDTO(Long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }
}
