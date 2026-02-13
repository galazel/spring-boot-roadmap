package com.event_management.even_management.user;

public record UserResponse(String lastname, String firstname, String email, String contact, String username, User.Role role, String errorMessage) {
}
