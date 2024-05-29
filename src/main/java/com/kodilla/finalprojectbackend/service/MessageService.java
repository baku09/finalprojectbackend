package com.kodilla.finalprojectbackend.service;

import com.kodilla.finalprojectbackend.communicator.Message;
import com.kodilla.finalprojectbackend.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message sendPrivateMessage(Message message) {
        // Implementacja wysyłania wiadomości prywatnych
        return messageRepository.save(message);
    }

    public List<Message> getAllPrivateMessagesByUser(Long userId) {
        // Implementacja pobierania wszystkich wiadomości prywatnych dla danego użytkownika
        return messageRepository.findAllByRecipientId(userId);
    }

    public List<Message> getAllPrivateMessagesByUserFromSender(Long userId, Long senderId) {
        // Implementacja pobierania wszystkich wiadomości prywatnych dla danego użytkownika od określonego nadawcy
        return messageRepository.findAllByRecipientIdAndSenderId(userId, senderId);
    }

    public Message sendGroupMessage(Message message) {
        // Implementacja wysyłania wiadomości grupowych
        return messageRepository.save(message);
    }

    public List<Message> getAllGroupMessages(Long groupId) {
        // Implementacja pobierania wszystkich wiadomości dla danej grupy
        return messageRepository.findAllByGroupId(groupId);
    }

    // Inne metody serwisu związane z obsługą wiadomości
}

