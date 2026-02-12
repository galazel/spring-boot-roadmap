package com.notepad.notepad.note;

import java.util.Date;

public record ResponseNote(String title, String content, Date dateCreated) {
}
