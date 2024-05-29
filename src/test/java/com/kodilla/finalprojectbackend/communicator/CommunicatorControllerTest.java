package com.kodilla.finalprojectbackend.communicator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodilla.finalprojectbackend.repository.GroupRepository;
import com.kodilla.finalprojectbackend.repository.MessageRepository;
import com.kodilla.finalprojectbackend.repository.UserRepository;
import com.kodilla.finalprojectbackend.service.GroupService;
import com.kodilla.finalprojectbackend.service.MessageService;
import com.kodilla.finalprojectbackend.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(CommunicatorController.class)
@AutoConfigureMockMvc
class CommunicatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private MessageRepository messageRepository;

    @MockBean
    private GroupRepository groupRepository;

    @MockBean
    private UserService userService;

    @MockBean
    private MessageService messageService;

    @MockBean
    private GroupService groupService;

    @Test
    void registerUser() throws Exception {
        // given
        User user = new User();
        user.setId(1L);
        user.setUsername("testUser");
        user.setEmail("test@example.com");

        when(userRepository.save(any(User.class))).thenReturn(user);

        // when and then
        mockMvc.perform(post("/api/users")
                        .content(asJsonString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.username").value("testUser"))
                .andExpect(jsonPath("$.email").value("test@example.com"));

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void getAllUsers() throws Exception {
        // given
        User user = new User();
        user.setId(1L);
        user.setUsername("testUser");
        user.setEmail("test@example.com");

        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        // when and then
        mockMvc.perform(get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].username").value("testUser"))
                .andExpect(jsonPath("$[0].email").value("test@example.com"));

        verify(userRepository, times(1)).findAll();
    }

    // Add more tests for other controller methods...

    // Helper method to convert object to JSON string
    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
