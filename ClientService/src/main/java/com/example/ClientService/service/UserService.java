package com.example.ClientService.service;

import com.example.ClientService.dtos.ClientLoginDto;
import com.example.ClientService.model.User;
import com.example.ClientService.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> login(ClientLoginDto clientLoginDto) {
        return userRepository.findByUsernameAndPassword(
                clientLoginDto.getUserName(),
                clientLoginDto.getPassword()
        );
    }

    public List<User> getAllEvents() {
        return userRepository.findAll();
    }

    public User createEvent(User user) {
        return userRepository.save(user);
    }

    public User updateProfile(Long userId, User updatedUser) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User existingUser = user.get();
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            // Add more fields if needed
            return userRepository.save(existingUser);
        } else {
            throw new RuntimeException("Client not found");
        }
    }

    public Optional<User> getClientById(Long userId) {
        return userRepository.findById(userId);
    }


    public User saveClient(User user) {
        return userRepository.save(user);
    }

    public Optional<User> getClientByUsername(String name) {
        return userRepository.findByName(name);
    }

    public Optional<User> getClientByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public User getUserInfo(String username) {
        return userRepository.findByUsername(username);
    }
}
