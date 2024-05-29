package com.kodilla.finalprojectbackend.communicator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    private Message message;

    @BeforeEach
    public void setUp() {
        User sender = new User("sender", "sender@example.com");
        User recipient = new User("recipient", "recipient@example.com");
        Group group = new Group();
        LocalDateTime sentAt = LocalDateTime.of(2024, 5, 29, 12, 0);

        message = new Message("Test message", sender, recipient, null, sentAt);
        message.setId(1L);
    }

    @Test
    public void testGetId() {
        assertEquals(1L, message.getId());
    }

    @Test
    public void testSetId() {
        message.setId(2L);
        assertEquals(2L, message.getId());
    }

    @Test
    public void testGetContent() {
        assertEquals("Test message", message.getContent());
    }

    @Test
    public void testSetContent() {
        message.setContent("Updated message");
        assertEquals("Updated message", message.getContent());
    }

    @Test
    public void testGetSender() {
        assertEquals("sender", message.getSender().getUsername());
    }

    @Test
    public void testSetSender() {
        User newSender = new User("newSender", "newSender@example.com");
        message.setSender(newSender);
        assertEquals("newSender", message.getSender().getUsername());
    }

    @Test
    public void testGetRecipient() {
        assertEquals("recipient", message.getRecipient().getUsername());
    }

    @Test
    public void testSetRecipient() {
        User newRecipient = new User("newRecipient", "newRecipient@example.com");
        message.setRecipient(newRecipient);
        assertEquals("newRecipient", message.getRecipient().getUsername());
    }

    @Test
    public void testGetGroup() {
        assertEquals(null, message.getGroup());
    }

    @Test
    public void testSetGroup() {
        Group newGroup = new Group();
        message.setGroup(newGroup);
        assertEquals(newGroup, message.getGroup());
    }

    @Test
    public void testGetSentAt() {
        LocalDateTime expectedDateTime = LocalDateTime.of(2024, 5, 29, 12, 0);
        assertEquals(expectedDateTime, message.getSentAt());
    }

    @Test
    public void testSetSentAt() {
        LocalDateTime newDateTime = LocalDateTime.of(2024, 5, 30, 12, 0);
        message.setSentAt(newDateTime);
        assertEquals(newDateTime, message.getSentAt());
    }

    @Test
    public void testEquals() {
        User sender = new User("sender", "sender@example.com");
        User recipient = new User("recipient", "recipient@example.com");
        Group group = new Group();
        LocalDateTime sentAt = LocalDateTime.of(2024, 5, 29, 12, 0);

        Message sameMessage = new Message("Test message", sender, recipient, group, sentAt);
        sameMessage.setId(1L);

        assertEquals(message, sameMessage);
    }

    @Test
    public void testHashCode() {
        User sender = new User("sender", "sender@example.com");
        User recipient = new User("recipient", "recipient@example.com");
        Group group = new Group();
        LocalDateTime sentAt = LocalDateTime.of(2024, 5, 29, 12, 0);

        Message sameMessage = new Message("Test message", sender, recipient, group, sentAt);
        sameMessage.setId(1L);

        assertEquals(message.hashCode(), sameMessage.hashCode());
    }

    @Test
    public void testToString() {
        String expectedString = "Message{id=1, content='Test message', sender=User{id=null, username='sender', email='sender@example.com'}, recipient=User{id=null, username='recipient', email='recipient@example.com'}, group=null, sentAt=2024-05-29T12:00}";
        assertEquals(expectedString, message.toString());
    }

}