package com.notepad.notepad.account;

import com.notepad.notepad.note.Note;
import com.notepad.notepad.note.RequestNote;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Service
public class UserServiceImp implements UserService{

    private final UserRepository userRepository;
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public void save(RequestUser userRequest) {
        User user = User.builder()
                .lastname(userRequest.lastname())
                .firstname(userRequest.firstname())
                .email(userRequest.email())
                .password(userRequest.password())
                .username(userRequest.username())
                .build();

        if(userRequest.notes() == null){
            userRepository.save(user);
        }else
        {
            List <Note> notes = userRequest.notes().stream().map(note -> Note.builder()
                    .title(note.title())
                    .content(note.content())
                    .dateCreated(note.dateCreated())
                    .user(user)
                    .build()).collect(Collectors.toList());

            user.setNotes(notes);
            userRepository.save(user);
        }

    }

    @Override
    public void delete(int id) {
        try {
            userRepository.deleteById(id);
        }catch (Exception e){
            throw new AccountException("Delete User with id " + id + " failed");
        }

    }

    @Override
    public ResponseUser findById(int id) {
        try
        {
            User user = userRepository.findById(id).get();
            return new ResponseUser(user.getUsername(), user.getEmail(), user.getFirstname(), user.getLastname(),null);
        }catch (Exception e){
            throw new AccountException("Find User with id " + id + " failed");
        }
    }

    @Override
    public List<ResponseUser> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> new ResponseUser(user.getUsername(),user.getEmail(),user.getFirstname(),user.getLastname(),null)).collect(Collectors.toUnmodifiableList());
    }

    @Override
    public void updateUser(int id, RequestUser note) {
        try{
            User user = userRepository.findById(id).get();
            user.setEmail(note.email());
            user.setFirstname(note.firstname());
            user.setLastname(note.lastname());
            user.setPassword(note.password());
            user.setUsername(note.username());
            userRepository.save(user);
        }catch (Exception e){
           throw new AccountException("Update User with id " + id + " failed");
        }
    }
}
