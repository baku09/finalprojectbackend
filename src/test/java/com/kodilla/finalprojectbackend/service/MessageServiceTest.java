package com.kodilla.finalprojectbackend.service;

import com.kodilla.finalprojectbackend.communicator.Message;
import com.kodilla.finalprojectbackend.repository.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MessageServiceTest {

    @InjectMocks
    private MessageService messageService;

    @Mock
    private MessageRepository messageRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void sendPrivateMessage() {
        // given
        Message message = new Message();
        when(messageRepository.save(message)).thenReturn(message);

        // when
        Message result = messageService.sendPrivateMessage(message);

        // then
        assertEquals(message, result);
    }

    @Test
    void getAllPrivateMessagesByUser() {
        // given
        Long userId = 1L;
        List<Message> messages = new ArrayList<>();
        messages.add(new Message());
        messages.add(new Message());
        when(messageRepository.findAllByRecipientId(userId)).thenReturn(messages);

        // when
        List<Message> result = messageService.getAllPrivateMessagesByUser(userId);

        // then
        assertEquals(2, result.size());
    }

    @Test
    void getAllPrivateMessagesByUserFromSender() {
        // given
        Long userId = 1L;
        Long senderId = 2L;
        List<Message> messages = new ArrayList<>();
        messages.add(new Message());
        messages.add(new Message());
        when(messageRepository.findAllByRecipientIdAndSenderId(userId, senderId)).thenReturn(messages);

        // when
        List<Message> result = messageService.getAllPrivateMessagesByUserFromSender(userId, senderId);

        // then
        assertEquals(2, result.size());
    }

    @Test
    void sendGroupMessage() {
        // given
        Message message = new Message();
        when(messageRepository.save(message)).thenReturn(message);

        // when
        Message result = messageService.sendGroupMessage(message);

        // then
        assertEquals(message, result);
    }

    @Test
    void getAllGroupMessages() {
        // given
        Long groupId = 1L;
        List<Message> messages = new ArrayList<>();
        messages.add(new Message());
        messages.add(new Message());
        when(messageRepository.findAllByGroupId(groupId)).thenReturn(messages);

        // when
        List<Message> result = messageService.getAllGroupMessages(groupId);

        // then
        assertEquals(2, result.size());
    }

}