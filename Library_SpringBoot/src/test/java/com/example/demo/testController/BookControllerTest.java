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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import com.example.demo.controller.BookController;
import com.example.demo.model.Book;
import com.example.demo.service.impl.BookServiceImpl;
import com.example.demo.service.impl.IssueBookServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
class BookControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private BookServiceImpl bookService;
	
	@MockBean
	private IssueBookServiceImpl issueBookService;
	
	@Test
	void displayBooksTest() throws Exception {
		
		mvc.perform(get("/books").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	
	@Test
	void displayBookByIdTest() throws Exception {
		
		mvc.perform(get("/books/1001").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	void displayBookByBookNameTest() throws Exception {
		
		mvc.perform(get("/books/title/Principia").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	void addNewBookTest() throws Exception {
		
		ObjectMapper objectMapper = new ObjectMapper();
		Book book = new Book(1001,"War and Peace","Mike Ryan", 500, "Available");
		
		mvc.perform(post("/books")
				.content(objectMapper.writeValueAsString(book))
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	void displayAllAvailableBooksTest() throws Exception {
		
		mvc.perform(get("/books/search/available").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		
	}
	
	@Test
	void deleteBookTest() throws Exception {
		
		mvc.perform(delete("/books/1001").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	
	@Test
	void displayBookByAuthorNameTest() throws Exception {
		
		mvc.perform(get("/books/author/Gulzar").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	void displayAllIssuedBooksTest() throws Exception {
		
		mvc.perform(get("/books/issued").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	

}
