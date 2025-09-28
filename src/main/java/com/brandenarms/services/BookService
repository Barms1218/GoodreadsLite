package com.brandenarms.services;

import com.brandenarms.repositories.BookRepository;
import com.brandenarms.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.brandenarms.models.Book;
import com.brandenarms.models.User;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class BookService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Autowired
    public BookService(UserRepository userRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> findByUserId(Long userId) {
        return bookRepository.findByUserID(userId);
    }

    public Optional<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public Optional<List<Book>> findByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    public Book updateBook(Long bookId, Book updatedBook) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book does not exist."));

        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());

        return bookRepository.save(book);
    }

    public Book updatePartialBook(Long bookId, String title, String author) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book does not exist."));

        if (title != null) {
            book.setTitle(title);
        }
        if (author != null) {
            book.setAuthor(author);
        }

        return bookRepository.save(book);
    }

    public void deleteByTitle(String title) {
        if (!bookRepository.existsByTitle(title)) {
            throw new RuntimeException("Book with that title does not exist.");
        }

         bookRepository.deleteByTitle(title);
    }

    public void deleteBook(Long bookId) {
        Book book;

        book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book does not exist"));

        bookRepository.delete(book);
    }

    public void deleteUserBook(Long userId, Long bookId) {
        Book book;
        book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book does not exist."));

        User user;
        user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User does not exist."));

        if (!Objects.equals(user.getId(), userId)) {
            return;
        }
            bookRepository.delete(book);
    }

    public int deleteBooksByAuthor(Long userId, String author) {
        List<Book> booksByAuthor = bookRepository.findByUserIdAndAuthor(userId, author);

        bookRepository.deleteAll(booksByAuthor);

        return booksByAuthor.size();
    }
}