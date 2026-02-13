package com.event_management.even_management.user;

import com.event_management.even_management.user.client.Client;
import com.event_management.even_management.user.client.ClientRepository;
import com.event_management.even_management.user.organizer.Organizer;
import com.event_management.even_management.user.organizer.OrganizerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final ClientRepository clientRepository;
    private final OrganizerRepository organizerRepository;

    public UserServiceImp(UserRepository userRepository,  ClientRepository clientRepository, OrganizerRepository organizerRepository) {
        this.userRepository = userRepository;
        this.clientRepository = clientRepository;
        this.organizerRepository = organizerRepository;
    }

    @Override
    public void addUser(UserRequest requestUser) {

        User user = User.builder()
                .email(requestUser.email())
                .contact(requestUser.contact())
                .role(requestUser.role())
                .lastname(requestUser.lastname())
                .firstname(requestUser.firstname())
                .username(requestUser.username())
                .password(requestUser.password())
                .build();

        if(requestUser.role().equals(User.Role.CLIENT)){
            Client client = Client.builder()
                    .user(user)
                    .joinedEvents(null)
                    .build();
            userRepository.save(user);
            clientRepository.save(client);

        }else
        {
            Organizer organizer = Organizer.builder()
                .user(user)
                    .organizedEvents(null)
                .build();
            userRepository.save(user);
            organizerRepository.save(organizer);

        }

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
        return userRepository.findAll().stream().map(user -> new UserResponse(user.getLastname(),user.getFirstname(),user.getEmail(), user.getContact(), user.getUsername(), user.getRole(),null)).collect(Collectors.toUnmodifiableList());
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
