package com.example.es12;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    private Controller controller;
    @Autowired
    private Repository repository;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createUser() throws Exception {
        User user = new User(1l, "pritvi", "udhin1998@gmail.com");
        mockMvc.perform(post("/users/create").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user))).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.username").value("pritvi"))
                .andExpect(jsonPath("$.email").value("udhin1998@gmail.com"));
    }

    @Test
    public void getUserById() throws Exception {
        User user = new User(1l, "pritvi", "udhin1998@gmail.com");
        User userSaved = repository.save(user);
        mockMvc.perform(get("/users/{id}", user.getId())).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()));
    }

    @Test
    public void updateUser() throws Exception {
		User user = new User(1l, "pritvi", "udhin1998@gmail.com");
		User userSaved = repository.save(user);
		User user2 = new User(1l, "mario", "mario1990@gmail.com");
		mockMvc.perform(put("/users/update/{id}", user.getId()).contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(user2))).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(user.getId()))
				.andExpect(jsonPath("$.username").value("mario"))
				.andExpect(jsonPath("$.email").value("mario1990@gmail.com"));
	}
	@Test
	public void deleteUserById () throws Exception {
		User user = new User(1l, "pritvi", "udhin1998@gmail.com");
		User userSaved = repository.save(user);
		mockMvc.perform(delete("/users/delete/{id}", user.getId())).andExpect(status().isOk());

		mockMvc.perform(get("/users/{id}", user.getId())).andExpect(status().isOk());
	}
}
