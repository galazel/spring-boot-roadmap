package com.notepad.notepad.account;

import com.notepad.notepad.note.RequestNote;
import com.notepad.notepad.note.ResponseNote;

import java.util.List;

public interface UserService {
    void save(RequestUser user);
    void delete(int id);
    ResponseUser findById(int id);
    List<ResponseUser> getAll();
    void updateUser(int id, RequestUser note);
}
