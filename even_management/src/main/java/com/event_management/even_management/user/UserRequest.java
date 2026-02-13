package com.event_management.even_management.user;

public record UserRequest(String lastname, String firstname, String email, String contact, String username, User.Role role, String password) {
}
