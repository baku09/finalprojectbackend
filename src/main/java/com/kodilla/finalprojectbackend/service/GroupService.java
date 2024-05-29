package com.kodilla.finalprojectbackend.service;

import com.kodilla.finalprojectbackend.communicator.Group;
import com.kodilla.finalprojectbackend.communicator.User;
import com.kodilla.finalprojectbackend.exception.ResourceNotFoundException;
import com.kodilla.finalprojectbackend.repository.GroupRepository;
import com.kodilla.finalprojectbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group getGroupById(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Group not found with id: " + id));
    }

    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }

    public Group updateGroup(Long id, Group group) {
        Group existingGroup = groupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Group not found with id: " + id));
        existingGroup.setName(group.getName());
        return groupRepository.save(existingGroup);
    }

    public void deleteGroup(Long id) {
        groupRepository.deleteById(id);
    }

    // Metody związane z użytkownikami grupy

    public List<User> getMembers(Long groupId) {
        Group group = getGroupById(groupId);
        return group.getMembers();
    }

    public boolean isUserMemberOfGroup(Long groupId, Long userId) {
        Group group = getGroupById(groupId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        return group.getMembers().contains(user);
    }

    public Group addMemberToGroup(Long groupId, Long userId) {
        Group group = getGroupById(groupId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        if (!group.getMembers().contains(user)) {
            group.getMembers().add(user);
            return groupRepository.save(group);
        } else {
            throw new IllegalArgumentException("User with id " + userId + " is already a member of group with id " + groupId);
        }
    }

    public Group removeMemberFromGroup(Long groupId, Long userId) {
        Group group = getGroupById(groupId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        if (group.getMembers().contains(user)) {
            group.getMembers().remove(user);
            return groupRepository.save(group);
        } else {
            throw new IllegalArgumentException("User with id " + userId + " is not a member of group with id " + groupId);
        }
    }
}
