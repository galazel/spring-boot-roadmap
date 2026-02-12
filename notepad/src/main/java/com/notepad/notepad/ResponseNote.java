package com.notepad.notepad;

import java.util.Date;

public record ResponseNote(String title, String content, Date dateCreated) {
}
