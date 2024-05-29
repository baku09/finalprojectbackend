package com.kodilla.finalprojectbackend.communicator;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GroupTest {

    @Test
    public void testGetId() {
        // Given
        Group group = new Group();
        Long id = 1L;
        group.setId(id);

        // When
        Long retrievedId = group.getId();

        // Then
        assertEquals(id, retrievedId);
    }

    @Test
    public void testGetName() {
        // Given
        Group group = new Group();
        String name = "Test Group";
        group.setName(name);

        // When
        String retrievedName = group.getName();

        // Then
        assertEquals(name, retrievedName);
    }

    @Test
    public void testGetMembers() {
        // Given
        Group group = new Group();
        User user1 = new User();
        user1.setUsername("user1");
        User user2 = new User();
        user2.setUsername("user2");
        List<User> members = new ArrayList<>();
        members.add(user1);
        members.add(user2);
        group.setMembers(members);

        // When
        List<User> retrievedMembers = group.getMembers();

        // Then
        assertEquals(members.size(), retrievedMembers.size());
        assertTrue(retrievedMembers.contains(user1));
        assertTrue(retrievedMembers.contains(user2));
    }

}