package com.example.es12;
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class UserControllerTest {
	@LocalServerPort
	private int port;
	@Autowired
	private Controller controller;
	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}
@Test
	public void restTemplateTest () {
		User user = this.restTemplate.getForObject("http://127.0.0.1:" + port + "/users/create", User.class);
		assertThat(user.getUsername()).contains("Pritvi");
		assertThat(user.getEmail()).contains("udhin1998@gmail.com");
}

@Test
	public void mockMvcTest () throws Exception {
		this.mockMvc.perform(get("/create")).andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.username").value("Pritvi"))
				.andExpect(jsonPath("$.email").value("udhin1998@gmail.com"))
				;
}
}
