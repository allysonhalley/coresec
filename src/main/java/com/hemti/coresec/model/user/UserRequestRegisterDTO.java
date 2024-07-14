package com.hemti.coresec.model.user;

public record UserRequestRegisterDTO(
        String username,
        String password,
        String email,
        UserRole role
) {
}
