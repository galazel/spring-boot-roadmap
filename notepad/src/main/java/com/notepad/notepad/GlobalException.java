package com.notepad.notepad;

import com.notepad.notepad.account.AccountException;
import com.notepad.notepad.account.ResponseUser;
import com.notepad.notepad.note.NoteException;
import com.notepad.notepad.note.ResponseNote;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(NoteException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseNote> handleException(NoteException ex) {
        return new ResponseEntity(new ResponseNote(null,null,null, ex.getMessage(), null), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(AccountException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseUser> handleException(AccountException ex) {
        return new ResponseEntity(new ResponseUser(null,null,null, null, ex.getMessage(), null), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> handleException(ConstraintViolationException ex) {
        Map<String,String> map = new HashMap<>();
        for (ConstraintViolation cv : ex.getConstraintViolations()) {
            map.put(cv.getPropertyPath().toString(), cv.getMessage());
        }
        return map;
    }
}
