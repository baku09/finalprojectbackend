package com.kodilla.finalprojectbackend.communicator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User("testUser", "test@example.com");
        user.setId(1L);
    }

    @Test
    public void testGetId() {
        assertEquals(1L, user.getId());
    }

    @Test
    public void testSetId() {
        user.setId(2L);
        assertEquals(2L, user.getId());
    }

    @Test
    public void testGetUsername() {
        assertEquals("testUser", user.getUsername());
    }

    @Test
    public void testSetUsername() {
        user.setUsername("newTestUser");
        assertEquals("newTestUser", user.getUsername());
    }

    @Test
    public void testGetEmail() {
        assertEquals("test@example.com", user.getEmail());
    }

    @Test
    public void testSetEmail() {
        user.setEmail("new@example.com");
        assertEquals("new@example.com", user.getEmail());
    }

    @Test
    public void testEquals() {
        User sameUser = new User("testUser", "test@example.com");
        sameUser.setId(1L);
        assertEquals(user, sameUser);
    }

    @Test
    public void testHashCode() {
        User sameUser = new User("testUser", "test@example.com");
        sameUser.setId(1L);
        assertEquals(user.hashCode(), sameUser.hashCode());
    }

    @Test
    public void testToString() {
        String expectedString = "User{id=1, username='testUser', email='test@example.com'}";
        assertEquals(expectedString, user.toString());
    }

}