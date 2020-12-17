package com.example.demo.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.BookNotFoundException;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.IBookService;


@Service
public class BookServiceImpl implements IBookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public List<Book> getAllBooks(){
		return bookRepository.findAll();
	}
	private static final String NOT_FOUND = "Book details not found for id "; 
	
	@Override
	public Book getBook(Integer bookId){
		return bookRepository.findById(bookId).orElseThrow(()->new BookNotFoundException(NOT_FOUND + bookId));
	}
	
	@Override
	public Book addBook(Book book){		
		return bookRepository.save(book);		
	}
	
	@Override
	public String deleteBook(Integer bookId) {
		Book bookDetails = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(NOT_FOUND+ bookId));
		bookDetails.setStatus("Not Available");
		bookRepository.save(bookDetails);
		return "Book Deleted Successfully!";
	}
	
	@Override
	public Book updateBook(Book book)
	{
		Book bookDetails = bookRepository.findById(book.getBookId()).orElseThrow(() -> new BookNotFoundException(NOT_FOUND+ book.getBookId()));
		return bookRepository.save(bookDetails);
	}
	
	@Override
	public List<Book> getBookByAuthor(String author) {
				return bookRepository.findByAuthor(author);
	}
	
	@Override
	public List<Book> getBookByBookName(String bookName) {
		return bookRepository.findByBookName(bookName);
	}

	@Override
	public List<Book> getAllAvailableBooks() {
		
		return bookRepository.findAllAvailableBooks();
	}
	

	
}
