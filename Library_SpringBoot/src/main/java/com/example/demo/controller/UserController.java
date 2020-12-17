package com.example.demo.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Book;
import com.example.demo.model.IssueBook;
import com.example.demo.model.User;
import com.example.demo.service.impl.BookServiceImpl;
import com.example.demo.service.impl.IssueBookServiceImpl;
import com.example.demo.service.impl.UserServiceImpl;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserController {
	
	@Autowired
	UserServiceImpl userService;
	
	@Autowired
	BookServiceImpl bookService;
	
	
	@Autowired
	IssueBookServiceImpl issueBookService;	
	

	@GetMapping("/users")
	public List<User> displayUsers() 
	{
		return  userService.getAllUsers();
	}
	
	@GetMapping("/users/{userId}")
	public User displayUserById(@PathVariable int userId){			
		return userService.getUser(userId);
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> addNewUser(@RequestBody User userDetails) {
		
		User user = userService.addUser(userDetails);
		HttpHeaders headers = new HttpHeaders();
		headers.add("response-form", "UserController");
		return new ResponseEntity<>(user,headers,HttpStatus.OK);
	}
	
	@DeleteMapping("/users/{userId}")
	public void deleteUser(@PathVariable int userId) {
	    userService.deleteUser(userId);
	    }
	
	@PutMapping("/users/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable int userId, @RequestBody User userDetails) {
		
		User user= userService.getUser(userId);
		user.setUserName(userDetails.getUserName());
		user.setEmail(userDetails.getEmail());		
		
		User userUpdated= userService.updateUser(user);		
		return  ResponseEntity.ok(userUpdated);
	} 
	

	@GetMapping("/users/name/{userName}")
	public List<User> displayUserByUserName(@PathVariable String userName){
		return userService.getUserByUserName(userName);
	}
	
	@PostMapping("/books/issue/{bookId}")
	public String issueTheBook(@PathVariable int bookId,@RequestBody IssueBook issuebook) {	
		
		Book book = bookService.getBook(bookId);
		String status= book.getStatus();
		if(status.equals("Available")) {		
		 issueBookService.issueTheBook(issuebook);
		 book.setStatus("Not Available");
		 bookService.updateBook(book);
		 return "Book Issued Successfully";
		
		}
		else {
			return "Book is not Available!!";
		}
	}
	
	@PutMapping("/books/return/{issueId}")
	public String returnBook(@PathVariable int issueId, @RequestBody IssueBook issueDetails) {
		
		IssueBook issueBook= issueBookService.getIssueDetails(issueId);
		issueBook.setReturnDate(issueDetails.getReturnDate());
		
		int bookId= issueBook.getBookId();
		Book book = bookService.getBook(bookId);
		book.setStatus("Available");
	    bookService.updateBook(book);
		
		int period= issueBook.getPeriod();
			
		LocalDate dateIssue = LocalDate.parse(issueBook.getIssueDate());
		LocalDate dateReturn = LocalDate.parse(issueDetails.getReturnDate());	
		
		int fine;			
		int noOfDaysBetween = (int) ChronoUnit.DAYS.between(dateIssue, dateReturn);
		
		if(noOfDaysBetween>period) {
			 fine= (noOfDaysBetween-period)*10;
		}
		else {
			 fine=0;
		}
				
		issueBook.setFine(fine);		
		
		issueBookService.returnTheBook(issueBook);
		
		
		return("Book Returned Successfully!!\nTotal Fine is: "+ fine);
		
		
	}
	
	@GetMapping("/books/issued/{userId}")
	public List<IssueBook> displayAllIssuedBooksByUserId(@PathVariable int userId)
	{
		return issueBookService.getAllIssuedBooksByUser(userId);
	}


}
