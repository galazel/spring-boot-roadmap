package com.notepad.notepad;

import java.util.Date;

public record RequestNote(String title, String content, Date dateCreated) {
}
