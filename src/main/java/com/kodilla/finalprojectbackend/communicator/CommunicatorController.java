package com.kodilla.finalprojectbackend.communicator;

import com.kodilla.finalprojectbackend.exception.ResourceNotFoundException;
import com.kodilla.finalprojectbackend.repository.GroupRepository;
import com.kodilla.finalprojectbackend.repository.MessageRepository;
import com.kodilla.finalprojectbackend.repository.UserRepository;
import com.kodilla.finalprojectbackend.service.GroupService;
import com.kodilla.finalprojectbackend.service.MessageService;
import com.kodilla.finalprojectbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CommunicatorController {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final GroupRepository groupRepository;
    private final UserService userService;
    private final MessageService messageService;
    private final GroupService groupService;

    @Autowired
    public CommunicatorController(UserRepository userRepository, MessageRepository messageRepository,
                                  GroupRepository groupRepository, UserService userService,
                                  MessageService messageService, GroupService groupService) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.groupRepository = groupRepository;
        this.userService = userService;
        this.messageService = messageService;
        this.groupService = groupService;
    }

    // Endpointy dotyczące użytkowników
    @PostMapping("/users")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User newUser = userRepository.save(user);
        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        // Update user properties
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        // Save updated user
        User updatedUser = userRepository.save(existingUser);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Endpointy dotyczące wiadomości prywatnych
    @PostMapping("/messages/private")
    public ResponseEntity<Message> sendPrivateMessage(@RequestBody Message message) {
        Message newMessage = messageService.sendPrivateMessage(message);
        return ResponseEntity.ok(newMessage);
    }

    @GetMapping("/messages/private/{userId}")
    public ResponseEntity<List<Message>> getPrivateMessagesByUser(@PathVariable Long userId) {
        List<Message> messages = messageRepository.findAllByRecipientId(userId);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/messages/private/{userId}/{senderId}")
    public ResponseEntity<List<Message>> getPrivateMessagesByUserFromSender(@PathVariable Long userId,
                                                                            @PathVariable Long senderId) {
        List<Message> messages = messageRepository.findAllByRecipientIdAndSenderId(userId, senderId);
        return ResponseEntity.ok(messages);
    }

    // Endpointy dotyczące grup
    @PostMapping("/groups")
    public ResponseEntity<Group> createGroup(@RequestBody Group group) {
        Group newGroup = groupService.createGroup(group);
        return ResponseEntity.ok(newGroup);
    }

    @GetMapping("/groups")
    public ResponseEntity<List<Group>> getAllGroups() {
        List<Group> groups = groupRepository.findAll();
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/groups/{id}")
    public ResponseEntity<Group> getGroupById(@PathVariable Long id) {
        Optional<Group> groupOptional = groupRepository.findById(id);
        return groupOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/groups/{id}")
    public ResponseEntity<Group> updateGroup(@PathVariable Long id, @RequestBody Group group) {
        Group existingGroup = groupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Group not found with id: " + id));
        // Update group properties
        existingGroup.setName(group.getName());
        // Save updated group
        Group updatedGroup = groupRepository.save(existingGroup);
        return ResponseEntity.ok(updatedGroup);
    }

    @DeleteMapping("/groups/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        groupRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Endpointy dotyczące wiadomości grupowych
    @PostMapping("/messages/group")
    public ResponseEntity<Message> sendGroupMessage(@RequestBody Message message) {
        Message newMessage = messageService.sendGroupMessage(message);
        return ResponseEntity.ok(newMessage);
    }

    @GetMapping("/messages/group/{groupId}")
    public ResponseEntity<List<Message>> getGroupMessages(@PathVariable Long groupId) {
        List<Message> messages = messageRepository.findAllByGroupId(groupId);
        return ResponseEntity.ok(messages);
    }

    @PutMapping("/messages/group/{messageId}")
    public ResponseEntity<Message> updateGroupMessage(@PathVariable Long messageId, @RequestBody Message message) {
        Message existingMessage = messageRepository.findById(messageId)
                .orElseThrow(() -> new ResourceNotFoundException("Message not found with id: " + messageId));
        // Update message properties
        existingMessage.setContent(message.getContent());
        // Save updated message
        Message updatedMessage = messageRepository.save(existingMessage);
        return ResponseEntity.ok(updatedMessage);
    }
    @DeleteMapping("/messages/group/{messageId}")
    public ResponseEntity<Void> deleteGroupMessage(@PathVariable Long messageId) {
        messageRepository.deleteById(messageId);
        return ResponseEntity.noContent().build();
    }
}