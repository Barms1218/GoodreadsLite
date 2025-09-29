package com.brandenarms.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.AssertTrue;

public class PasswordChangeDTO {
    @NotBlank(message = "Username is required.")
    private String username;

    @NotBlank(message = "Enter your old password")
    private String oldPassword;

    @NotBlank(message = "New password is required.")
    @Size(min = 8, message = "Password must be at least 8 characters long.")
    String newPasswordOne;

    @NotBlank(message = "Make sure this matches your new password")
    @Size(min = 8, message = "Password must be at least 8 characters long.")
    String newPasswordTwo;

    public PasswordChangeDTO() {

    }

    public PasswordChangeDTO(String username, String newPasswordOne) {
        this.username = username;
        this.newPasswordOne = newPasswordOne;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPasswordOne() {
        return newPasswordOne;
    }

    public void setNewPasswordOne(String newPasswordOne) {
        this.newPasswordOne = newPasswordOne;
    }

    @AssertTrue(message = "New password and confirmation do not match!")
    public boolean passwordsMatch() {
        boolean match = newPasswordOne != null && newPasswordOne.equals(newPasswordTwo);

        return match;
    }
}

