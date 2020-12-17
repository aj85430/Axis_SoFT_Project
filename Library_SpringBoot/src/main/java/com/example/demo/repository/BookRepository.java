package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Book;


@Repository
public interface BookRepository extends JpaRepository<Book, Integer>  {

	List<Book> findByAuthor(String author);

	List<Book> findByBookName(String bookName);

	@Query("select t from Book t where t.status='Available' ")
	List<Book> findAllAvailableBooks();

}
