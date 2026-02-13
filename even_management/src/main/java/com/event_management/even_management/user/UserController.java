package com.event_management.even_management.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("add")
    public void addUser(@RequestBody UserRequest userRequest) {
        userService.addUser(userRequest);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("get-all")
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }
}
