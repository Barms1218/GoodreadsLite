package com.brandenarms.services;

import com.brandenarms.dtos.UserResponseDTO;
import com.brandenarms.dtos.UserLoginDTO;
import com.brandenarms.dtos.PasswordChangeDTO;
import com.brandenarms.repositories.BookRepository;
import com.brandenarms.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.brandenarms.models.User;

//import org.springframework.security.crypto.bcrypt.BcryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Autowired
    public UserService(BookRepository bookRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }


    public UserResponseDTO registerUser(String username, String password) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User newUser = new User();

        newUser.setUsername(username);

        String hashedPassword = passwordEncoder.encode(password);
        newUser.setPasswordHash(hashedPassword);

        userRepository.save(newUser);

        return new UserResponseDTO(username);
    }

    public boolean loginUser(UserLoginDTO dto) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User existingUser = userRepository.findByUserName(dto.getUsername())
                .orElseThrow(() -> new RuntimeException("User.java not found."));

        return passwordEncoder.matches(existingUser.getPasswordHash(), dto.getPassword());
    }

    public void changePassword(PasswordChangeDTO dto) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = userRepository.findByUserName(dto.getUsername())
                .orElseThrow(() -> new RuntimeException("Username does not exist"));

        if (encoder.matches(dto.getOldPassword(), user.getPasswordHash())) {
            throw new IllegalArgumentException("Old password is incorrect.");
        }

        String hashedPassword = encoder.encode(dto.getNewPasswordOne());
        user.setPasswordHash(hashedPassword);

        userRepository.save(user);
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUser(Long userId) {
        User user;

        user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User.java does not exist."));

        userRepository.delete(user);
    }
}
