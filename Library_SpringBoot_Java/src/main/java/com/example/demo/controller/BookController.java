package com.example.demo.controller;

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
import com.example.demo.service.impl.BookServiceImpl;
import com.example.demo.service.impl.IssueBookServiceImpl;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class BookController {
	
	@Autowired
	BookServiceImpl bookService;	
	
	@Autowired
	IssueBookServiceImpl issueBookService;	
	
	
	@GetMapping("/books")
	public List<Book> displayBooks() 
	{
		return  bookService.getAllBooks();
	}
	
	@GetMapping("/books/{bookId}")
	public Book displayBookById(@PathVariable Integer bookId){			
		return bookService.getBook(bookId);
	}
	
	@PostMapping("/books")
	public ResponseEntity<Book> addNewBook(@RequestBody Book bookDetails) {
		
		Book book = bookService.addBook(bookDetails);
		HttpHeaders headers = new HttpHeaders();
		headers.add("response-form", "BookController");
		return new ResponseEntity<>(book,headers,HttpStatus.OK);
	}
	
	@DeleteMapping("/books/{bookId}")
	public String deleteBook(@PathVariable Integer bookId) {
	    bookService.deleteBook(bookId);
	    return "Book Deleted Successfully";
	 }
	
	@PutMapping("/books/{bookId}")
	public ResponseEntity<Book> updateBook(@PathVariable Integer bookId, @RequestBody Book bookDetails) {
		
		Book book= bookService.getBook(bookId);
		book.setBookName(bookDetails.getBookName());
		
		book.setAuthor(bookDetails.getAuthor());
		book.setPrice(bookDetails.getPrice());
		book.setStatus(bookDetails.getStatus());
		
		Book bookUpdated= bookService.updateBook(book);
		
		
		return  ResponseEntity.ok(bookUpdated);
	} 
	
	@GetMapping("/books/author/{author}")
	public List<Book> displayBookByAuthor(@PathVariable String author){
		return bookService.getBookByAuthor(author);
	}

	@GetMapping("/books/title/{bookName}")
	public List<Book> displayBookByBookName(@PathVariable String bookName){
		return bookService.getBookByBookName(bookName);
	}

	@GetMapping("/books/issued")
	public List<IssueBook> displayAllIssuedBooks()
	{
		return issueBookService.getAllIssuedBooks();
	}
	
	@GetMapping("/books/search/available")
	public List<Book> displayAllAvailableBooks()
	{
		return bookService.getAllAvailableBooks();
	}
	
	
	
	

}
