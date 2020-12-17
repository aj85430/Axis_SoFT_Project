package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.IssueBook;
import com.example.demo.repository.IssueBookRepository;
import com.example.demo.service.IIssueBookService;



@Service
public class IssueBookServiceImpl implements IIssueBookService {
	
	@Autowired
	private IssueBookRepository issueBookRepository;
	
	@Override
	public IssueBook getIssueDetails(Integer issueId){
		return issueBookRepository.findById(issueId).orElseThrow();
	}
	
	@Override
	public List<IssueBook> getAllIssuedBooks(){
		return issueBookRepository.findAll();
	}
	
	@Override
    public String issueTheBook(IssueBook issueBook){
		
		issueBookRepository.save(issueBook);
		return "Book issued successfully";
		
	}
    
	@Override
    public String returnTheBook(IssueBook issueBook){
		
		issueBookRepository.save(issueBook);
		return "Book returned successfully";
		
	}
    
	@Override
    public List<IssueBook> getAllIssuedBooksByUser(int userId) {
		return issueBookRepository.findAllByUserId(userId);
	}
}
