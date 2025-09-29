package com.brandenarms.dtos;

public class UserResponseDTO {
    private Long id;
    private String username;

    public UserResponseDTO(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
