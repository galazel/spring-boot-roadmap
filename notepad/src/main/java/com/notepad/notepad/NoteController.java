package com.notepad.notepad;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class NoteController  {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("save")
    public void saveNote(@Valid @RequestBody RequestNote requestNote)
    {
        noteService.save(requestNote);
    }
    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNote(@PathVariable int id)
    {
        noteService.delete(id);
    }

    @GetMapping("find/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseNote findById(@PathVariable int id)
    {
        return noteService.findById(id);
    }

    @GetMapping("findAll")
    @ResponseStatus(HttpStatus.OK)
    public List<ResponseNote> getAll()
    {
        return noteService.getAll();
    }
    @PutMapping("update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateNote(@PathVariable int id, @Valid @RequestBody RequestNote requestNote)
    {
        noteService.updateNote(id, requestNote);
    }


}
