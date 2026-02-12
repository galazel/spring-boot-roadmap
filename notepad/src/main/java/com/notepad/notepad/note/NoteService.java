package com.notepad.notepad.note;

import java.util.List;

public interface NoteService {
    void save(RequestNote note);
    void delete(int id);
    ResponseNote findById(int id);
    List<ResponseNote> getAll();
    void updateNote(int id, RequestNote note);
}
