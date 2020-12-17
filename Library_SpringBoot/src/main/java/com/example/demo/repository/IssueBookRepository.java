package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.IssueBook;



@Repository
public interface IssueBookRepository extends JpaRepository<IssueBook, Integer> {

	List<IssueBook> findAllByUserId(Integer userId);

	 
	


}
