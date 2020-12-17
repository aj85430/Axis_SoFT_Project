package com.example.demo.testService;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.impl.BookServiceImpl;

@SpringBootTest
class BookServiceTest {
	
	@Autowired
	BookServiceImpl bookService;
	
	@MockBean
	private BookRepository bookRepository;
	
	@Test
	void getAllBooksTest() {
		
		List<Book> bookMockList = new ArrayList<>();
		
		bookMockList.add(new Book(1001,"War and Peace","Mike Ryan", 500, "Available"));
		bookMockList.add(new Book(1002,"Game Of Thrones","George Martin", 1500, "Available"));
		bookMockList.add(new Book(1003,"Love Physics","Lokie Sams", 300, "Available"));
		
		when(bookRepository.findAll()).thenReturn(bookMockList);
		
		assertEquals(3, bookService.getAllBooks().size());
		
	}
	
	@Test
	void addBookTest() {
		
		Book book = new Book(1005,"Kotlin","Luke Garry",180,"Available");
		when(bookRepository.save(book)).thenReturn(book);
		
		assertEquals(book, bookService.addBook(book));
		
	}
	
	@Test
	void getBookTest() {
		Book book = new Book(1001,"War and Peace","Mike Ryan", 500, "Available");
		int id=1001;
		when(bookRepository.findById(id)).thenReturn(Optional.of(book));
		assertEquals(book, bookService.getBook(id));
		
	}
	
	
	@Test
	void getBookByBookNameTest() {
		
		List<Book> bookMockList = new ArrayList<>();		
		bookMockList.add(new Book(1001,"War and Peace","Mike Ryan", 500, "Available"));	
		String name= "War and Peace";		
		when(bookRepository.findByBookName(name)).thenReturn(bookMockList);		
		assertEquals(name, bookService.getBookByBookName(name).get(0).getBookName());
		
	}
	
	@Test
	void getBookByAuthorTest() {
		
        List<Book> bookMockList = new ArrayList<>();		
		bookMockList.add(new Book(1001,"War and Peace","Mike Ryan", 500, "Available"));
		bookMockList.add(new Book(1002,"Love Physics","Mike Ryan", 300, "Available"));
		String author= "Mike Ryan";
		when(bookRepository.findByAuthor(author)).thenReturn(bookMockList);
		assertEquals(2, bookService.getBookByAuthor(author).size());
		
	}
	
	@Test
	void getAllAvailableBooksTest() {
		
		List<Book> bookMockList = new ArrayList<>();		
		bookMockList.add(new Book(1001,"War and Peace","Mike Ryan", 500, "Available"));
		bookMockList.add(new Book(1002,"Love Physics","Mike Ryan", 300, "Available"));
		bookMockList.add(new Book(1003,"MindHunter","George bell", 400, "Available"));
	
		when(bookRepository.findAllAvailableBooks()).thenReturn(bookMockList);
		assertEquals(3, bookService.getAllAvailableBooks().size());
		
	}
	
	
	
	
	

}
