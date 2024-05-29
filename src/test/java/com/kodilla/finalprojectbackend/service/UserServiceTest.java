package com.kodilla.finalprojectbackend.service;

import com.kodilla.finalprojectbackend.communicator.User;
import com.kodilla.finalprojectbackend.exception.ResourceNotFoundException;
import com.kodilla.finalprojectbackend.factory.UserFactory;
import com.kodilla.finalprojectbackend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserFactory userFactory;

    private User testUser;

    @BeforeEach
    public void setUp() {
        testUser = new User("testUser", "test@example.com");
    }

    @Test
    public void shouldCreateNewUser() {
        // given
        String username = "testUser";
        String email = "test@example.com";
        when(userFactory.createUser(username, email)).thenReturn(testUser);

        // when
        userService.createUser(username, email);

        // then
        verify(userRepository).save(testUser);
    }
}