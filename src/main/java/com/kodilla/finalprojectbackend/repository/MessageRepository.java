package com.kodilla.finalprojectbackend.repository;

import com.kodilla.finalprojectbackend.communicator.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByRecipientId(Long recipientId);
    List<Message> findAllByRecipientIdAndSenderId(Long recipientId, Long senderId);
    List<Message> findAllByGroupId(Long groupId);

}
