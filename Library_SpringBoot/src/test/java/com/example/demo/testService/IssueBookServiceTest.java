package com.example.demo.testService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import com.example.demo.model.IssueBook;
import com.example.demo.repository.IssueBookRepository;
import com.example.demo.service.impl.IssueBookServiceImpl;

@SpringBootTest
class IssueBookServiceTest {

	@Autowired
	IssueBookServiceImpl issueBookService;
	
	@MockBean
	private IssueBookRepository issueBookRepository;
	
	@Test
	void issueTheBookTest() {
		
		IssueBook issueBook = new IssueBook(1,50001,1001,"2020-11-10","",3,0);
		issueBookService.issueTheBook(issueBook);
		verify(issueBookRepository, times(1)).save(issueBook);
		
	}
	
	@Test
	void returnTheBookTest() {
		
		IssueBook issueBook = new IssueBook(1,50001,1001,"2020-11-10","2020-15-10",3,20);
		issueBookService.returnTheBook(issueBook);
		verify(issueBookRepository, times(1)).save(issueBook);
		
	}
	
	@Test
	void getIssueDetailsTest() {
		int issueId = 10;
		IssueBook issueBook = new IssueBook(1,50001,1001,"2020-11-10","2020-15-10",3,20);
		when(issueBookRepository.findById(issueId)).thenReturn(Optional.of(issueBook));
		assertEquals(issueBook, issueBookService.getIssueDetails(issueId));
	}
	

	@Test
	void getAllIssuedBooksTest() {
		
		List<IssueBook> bookMockList = new ArrayList<>();
		
		bookMockList.add(new IssueBook(1,50001,1001,"2020-11-10","2020-15-10",3,20));
		bookMockList.add(new IssueBook(2,50002,1002,"2020-11-12","2020-11-14",1,10));
		
		when(issueBookRepository.findAll()).thenReturn(bookMockList);
		
		assertEquals(2, issueBookService.getAllIssuedBooks().size());
		
	}
	
	
	@Test
	void getAllIssuedBooksByUserTest() {
		int userId = 50001;
        List<IssueBook> bookMockList = new ArrayList<>();		
		bookMockList.add(new IssueBook(1,50001,1001,"2020-11-10","2020-15-10",3,20));
		bookMockList.add(new IssueBook(2,50001,1002,"2020-11-12","2020-11-14",1,10));
		when(issueBookRepository.findAllByUserId(userId)).thenReturn(bookMockList);
		assertEquals(2, issueBookService.getAllIssuedBooksByUser(userId).size());
	}
	
	
	
	
	

}
