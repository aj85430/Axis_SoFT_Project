package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="issued")
public class IssueBook {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int issueId;
	private int userId;
	private int bookId;
	private String issueDate;
	private String returnDate;
	private int period;
	private int fine;
	
	
	public IssueBook() {}
	public IssueBook(int issueId, int userId, int bookId, String issueDate, String returnDate, int period, int fine) {
		super();
		this.issueId = issueId;
		this.userId = userId;
		this.bookId = bookId;
		this.issueDate = issueDate;
		this.returnDate = returnDate;
		this.period = period;
		this.fine = fine;
	}
	
	public int getIssueId() {
		return issueId;
	}
	public void setIssueId(int issueId) {
		this.issueId = issueId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public int getFine() {
		return fine;
	}
	public void setFine(int fine) {
		this.fine = fine;
	}
	
	

}
