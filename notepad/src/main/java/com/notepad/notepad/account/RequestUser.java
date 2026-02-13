package com.notepad.notepad.account;

import com.notepad.notepad.note.Note;
import com.notepad.notepad.note.RequestNote;

import java.util.List;

public record RequestUser(String username, String password, String email, String firstname, String lastname, List<RequestNote> notes) {
}
