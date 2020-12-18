package com.manipal.demo.service

import com.manipal.demo.model.IssueBook

interface IIssueBookService {

    fun getIssueDetails(issueId: Int): IssueBook

    fun getAllIssuedBooks(): List<IssueBook>

    fun issueTheBook(issueBook: IssueBook): String

    fun returnTheBook(issueBook: IssueBook): String

    fun getAllIssuedBooksByUser(userId: Int): List<IssueBook>
}