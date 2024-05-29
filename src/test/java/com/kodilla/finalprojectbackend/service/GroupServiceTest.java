package com.kodilla.finalprojectbackend.service;

import com.kodilla.finalprojectbackend.communicator.Group;
import com.kodilla.finalprojectbackend.repository.GroupRepository;
import com.kodilla.finalprojectbackend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GroupServiceTest {

    private GroupService groupService;
    private GroupRepository groupRepository;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        groupRepository = mock(GroupRepository.class);
        userRepository = mock(UserRepository.class);
        groupService = new GroupService(groupRepository, userRepository);
    }

    @Test
    void getAllGroups() {
        // given
        List<Group> groups = new ArrayList<>();
        groups.add(new Group());
        groups.add(new Group());
        when(groupRepository.findAll()).thenReturn(groups);

        // when
        List<Group> result = groupService.getAllGroups();

        // then
        assertEquals(2, result.size());
    }

    @Test
    void getGroupById() {
        // given
        Long groupId = 1L;
        Group group = new Group();
        group.setId(groupId);
        when(groupRepository.findById(groupId)).thenReturn(Optional.of(group));

        // when
        Group result = groupService.getGroupById(groupId);

        // then
        assertEquals(groupId, result.getId());
    }

    @Test
    void createGroup() {
        // given
        Group group = new Group();
        when(groupRepository.save(group)).thenReturn(group);

        // when
        Group result = groupService.createGroup(group);

        // then
        assertNotNull(result);
    }

    @Test
    void updateGroup() {
        // given
        Long groupId = 1L;
        Group existingGroup = new Group();
        existingGroup.setId(groupId);
        Group updatedGroup = new Group();
        updatedGroup.setId(groupId);
        updatedGroup.setName("Updated Name");
        when(groupRepository.findById(groupId)).thenReturn(Optional.of(existingGroup));
        when(groupRepository.save(existingGroup)).thenReturn(updatedGroup);

        // when
        Group result = groupService.updateGroup(groupId, updatedGroup);

        // then
        assertEquals(updatedGroup.getName(), result.getName());
    }

    @Test
    void deleteGroup() {
        // given
        Long groupId = 1L;

        // when
        groupService.deleteGroup(groupId);

        // then
        verify(groupRepository, times(1)).deleteById(groupId);
    }

}