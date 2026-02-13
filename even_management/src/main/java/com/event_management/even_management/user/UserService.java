package com.event_management.even_management.user;

import java.util.List;

public interface UserService {
    void addUser(UserRequest requestUser);
    void deleteUser(int id);
    UserResponse getUser(int id);
    List<UserResponse> getAllUsers();
    List<UserResponse> getAllClientsInEvent(int id);
    List<UserResponse> getAllEventsOfOrganizer(int id);
}
