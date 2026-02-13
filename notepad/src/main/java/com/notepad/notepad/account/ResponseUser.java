package com.notepad.notepad.account;

import com.notepad.notepad.note.Note;

import java.util.List;

public record ResponseUser(String username, String email, String firstname, String lastname, String errorMessage, List<Note> notes) {
}
