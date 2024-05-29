package com.kodilla.finalprojectbackend.factory;

import com.kodilla.finalprojectbackend.communicator.User;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {

    public User createUser(String username, String email) {

        return new User(username, email);
    }
}
