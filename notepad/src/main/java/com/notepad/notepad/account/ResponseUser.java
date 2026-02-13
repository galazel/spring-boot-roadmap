package com.notepad.notepad.account;

public record ResponseUser(String username, String email, String firstname, String lastname, String errorMessage) {
}
