package com.products.dto;

import com.products.enums.UserRole;

public record RegisterDTO(String username, String password, UserRole role) {
}
