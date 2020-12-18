package com.example.demo.service;

import java.util.List;

import com.example.demo.model.IssueBook;

public interface IIssueBookService {
	
	public IssueBook getIssueDetails(Integer issueId);
	
	public List<IssueBook> getAllIssuedBooks();
	
	
    public String issueTheBook(IssueBook issueBook);
    
    public String returnTheBook(IssueBook issueBook);
    
    public List<IssueBook> getAllIssuedBooksByUser(int userId);
}
