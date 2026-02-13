package com.notepad.notepad.note;

import com.notepad.notepad.account.User;
import com.notepad.notepad.account.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImp implements NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public NoteServiceImp(NoteRepository noteRepository,  UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void save(RequestNote note) {
        try
        {
            User user = userRepository.findById(note.userId()).get();
            noteRepository.save(Note.builder()
                    .title(note.title())
                    .content(note.content())
                    .dateCreated(note.dateCreated())
                    .user(user)
                    .build());
        } catch (Exception e) {
            throw new NoteException("User not found");
        }

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
        return new ResponseNote(note.getTitle(),note.getContent(), note.getDateCreated(), null,note.getUser());
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
        noteRepository.save(note);
    }



}
