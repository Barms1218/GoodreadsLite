package com.brandenarms.repositories;

import com.brandenarms.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByUserID(Long userId);
    List<Book> findByTitleIgnoreCase(String title);
    List<Book> findByAuthorContaining(String author);
    List<Book> findByUserIDAndTitleContaining(Long userId, String title);
    List<Book> findByUserIdAndAuthor(Long userId, String author);

    void deleteByTitle(String title);
    int deleteByAuthor(String author); // Return the number of books deleted

    Optional<Book> findByTitle(String title);

    boolean existsByTitle(String title);

    @Query("SELECT b FROM b WHERE b.title LIKE %?1%")
    Book findBookContainingTitle(String searchTerm);

    Optional<List<Book>> findByAuthor(String author);

    boolean existsByAuthor(String author);

    @Query("SELECT b from b WHERE b.author LIKE %?1%")
    List<Book> findBooksWithAuthor(String author);



}
