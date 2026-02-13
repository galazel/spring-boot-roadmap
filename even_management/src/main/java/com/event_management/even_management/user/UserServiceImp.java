package com.event_management.even_management.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Override
    public void addUser(UserRequest requestUser) {

    }

    @Override
    public void deleteUser(int id) {

    }

    @Override
    public UserResponse getUser(int id) {
        return null;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return List.of();
    }

    @Override
    public List<UserResponse> getAllClientsInEvent(int id) {
        return List.of();
    }

    @Override
    public List<UserResponse> getAllEventsOfOrganizer(int id) {
        return List.of();
    }
}
