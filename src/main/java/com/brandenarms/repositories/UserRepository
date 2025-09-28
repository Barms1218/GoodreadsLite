package com.brandenarms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.brandenarms.models.User;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {

    // Spring Data automatically implements basic CRUD operations:
    // save(User user)
    // findById(Long id)
    // findAll()
    // deleteById(Long id)
    // count()
    // existsById(Long id)

    Optional<User> findByUserName(String username);

    boolean existsByUsername(String username);

    @Query("SELECT u FROM u WHERE u.username LIKE %?1%")
    List<User> findUserNameContaining(String searchTerm);
}
