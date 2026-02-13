package com.notepad.notepad.note;

import com.notepad.notepad.account.User;

import java.util.Date;

public record RequestNote(String title, String content, Date dateCreated, int userId) {
}
