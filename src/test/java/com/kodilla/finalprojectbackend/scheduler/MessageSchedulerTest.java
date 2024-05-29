package com.kodilla.finalprojectbackend.scheduler;

import com.kodilla.finalprojectbackend.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class MessageSchedulerTest {

    @Test
    public void testDeleteGroupMessages() {
        // Given
        MessageRepository messageRepository = mock(MessageRepository.class);
        MessageScheduler messageScheduler = new MessageScheduler(messageRepository);

        // When
        messageScheduler.deleteGroupMessages();

        // Then
        verify(messageRepository, times(1)).deleteAll();
    }
}