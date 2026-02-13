package com.notepad.notepad.note;

import com.notepad.notepad.account.User;

import java.util.Date;

public record ResponseNote(String title, String content, Date dateCreated, String errorMessage, User currentUser) {
}
