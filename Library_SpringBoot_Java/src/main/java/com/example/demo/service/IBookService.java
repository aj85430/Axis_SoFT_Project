package com.example.demo.service;

import java.util.List;


import com.example.demo.model.Book;

public interface IBookService {
	
	public List<Book> getAllBooks();
	
	public Book getBook(Integer bookId);
	
	public Book addBook(Book book);
	
	
	public String deleteBook(Integer bookId);
	
	public Book updateBook(Book book);
	
	public List<Book> getBookByAuthor(String author);
	
	public List<Book> getBookByBookName(String bookName);
	
	public List<Book> getAllAvailableBooks();
	



}
