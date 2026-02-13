package com.notepad.notepad.account;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("save")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody RequestUser user){
        userService.save(user);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("delete/{int}")
    public void delete(@PathVariable  int id){
        userService.delete(id);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("user/{id}")
    public ResponseUser findById(@PathVariable int id){
        return userService.findById(id);
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("get-users")
    public List<ResponseUser> getAll(){
        return userService.getAll();
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("update")
    public void updateNote(int id, @Valid @RequestBody RequestUser note){
        userService.updateUser(id, note);
    }

}
