package com.brandenarms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import java.util.List;
import com.brandenarms.models.User;


public interface UserRepository extends JpaRepository<User, Long> {

    // Spring Data automatically implements basic CRUD operations:
    // save(User.java user)
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
