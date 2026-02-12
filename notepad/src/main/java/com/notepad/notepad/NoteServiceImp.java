package com.notepad.notepad;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImp implements NoteService {

    private final NoteRepository noteRepository;

    public NoteServiceImp(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public void save(RequestNote note) {
        noteRepository.save(Note.builder()
                .title(note.title())
                .content(note.content())
                .dateCreated(note.dateCreated())
                .build());
    }

    @Override
    public void delete(int id){
        try {
            noteRepository.deleteById(id);
        }catch (Exception e){
            throw new NoteException("Note could not be deleted.");
        }
    }

    @Override
    public ResponseNote findById(int id) {
        Note  note = noteRepository.findById(id).orElse(null);
        if(note == null){
            throw new NoteException("Note could not be found.");
        }
        return new ResponseNote(note.getTitle(),note.getContent(), note.getDateCreated());
    }

    @Override
    public List<ResponseNote> getAll() {
        return noteRepository.findAllBy();
    }

    @Override
    public void updateNote(int id, RequestNote responseNote) {
        Note note = noteRepository.findById(id).orElse(null);
        if(note == null){
            throw new NoteException("Note could not be found.");
        }
        note.setTitle(responseNote.title());
        note.setContent(responseNote.content());
    }



}
