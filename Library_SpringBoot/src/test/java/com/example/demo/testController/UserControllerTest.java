package com.example.demo.testController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.controller.UserController;
import com.example.demo.model.User;
import com.example.demo.service.impl.BookServiceImpl;
import com.example.demo.service.impl.IssueBookServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {
	
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private UserServiceImpl userService;
	
	@MockBean
	private BookServiceImpl bookService;
	
	@MockBean
	private IssueBookServiceImpl issueBookService;
	

	@Test
	void displayUsersTest() throws Exception {
		
		mvc.perform(get("/users").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	void displayUserByIdTest() throws Exception {
		
		mvc.perform(get("/users/50001").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	void displayUserByUserNameTest() throws Exception {
		
		mvc.perform(get("/users/name/Aman Jaiswal").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	void deleteUserTest() throws Exception {
		
		mvc.perform(delete("/users/50001").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}

	@Test
	void addNewUserTest() throws Exception {
		
		ObjectMapper objectMapper = new ObjectMapper();
		User user = new User(50001,"Aman Jaiswal","abc@gmail.com");
		
		mvc.perform(post("/users")
				.content(objectMapper.writeValueAsString(user))
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	void displayAllIssuedBooksByUserIdTest() throws Exception{

		mvc.perform(get("/books/issued/50001").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
}
