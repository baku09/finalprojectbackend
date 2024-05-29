package com.kodilla.finalprojectbackend.service;

import com.kodilla.finalprojectbackend.communicator.User;
import com.kodilla.finalprojectbackend.exception.ResourceNotFoundException;
import com.kodilla.finalprojectbackend.factory.UserFactory;
import com.kodilla.finalprojectbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserFactory userFactory;

    @Autowired
    public UserService(UserRepository userRepository, UserFactory userFactory) {
        this.userRepository = userRepository;
        this.userFactory = userFactory;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(String username, String email) {
        User newUser = userFactory.createUser(username, email);
        return userRepository.save(newUser);
    }

    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        // Update user properties
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        // Save updated user
        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

