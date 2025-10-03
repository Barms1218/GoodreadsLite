package com.brandenarms.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(name="email", nullable = false, unique = true)
    private String email = "";

    @Column(name = "password_hash", nullable = false)
    private String passwordHash = "";

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> bookList = new ArrayList<>();

    public User() {

    }

    public User(String username, String passwordHash) {
        this.email = username;
        this.passwordHash = passwordHash;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String hash) {
        this.passwordHash = hash;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void addBook(Book book) {
        bookList.add(book);
        book.setUser(this);
    }

    public void removeBook(Book book) {
        bookList.remove(book);
        book.setUser(null);
    }
}
