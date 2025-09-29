package com.brandenarms.models;

import jakarta.persistence.*;

enum ReadingStatus {
    DONE,
    TO_READ,
    WANT_TO_READ,
    DID_NOT_FINISH
}

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, unique = true)
    private String title = "";

    @Column(name = "author", nullable = false)
    private String author = "";

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = true)
    private ReadingStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "userId", nullable = false, unique = true)
    private User user;

    public Book() {

    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public ReadingStatus getStatus() {
        return status;
    }

    public void setStatus(ReadingStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
