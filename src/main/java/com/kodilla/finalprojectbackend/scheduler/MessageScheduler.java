package com.kodilla.finalprojectbackend.scheduler;

import com.kodilla.finalprojectbackend.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MessageScheduler {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageScheduler(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Scheduled(cron = "0 0 0 1 */1 *") // Uruchamia o północy pierwszego dnia każdego miesiąca
    public void deleteGroupMessages() {
        // Usuwanie wszystkich wiadomości z grup
        messageRepository.deleteAll();
    }
}
