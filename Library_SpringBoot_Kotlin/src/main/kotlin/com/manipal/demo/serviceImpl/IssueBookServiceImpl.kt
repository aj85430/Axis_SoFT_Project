package com.manipal.demo.serviceImpl

import com.manipal.demo.model.IssueBook
import com.manipal.demo.repository.IssueBookRepository
import com.manipal.demo.service.IIssueBookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
public class IssueBookServiceImpl: IIssueBookService{

    @Autowired
    private val issueBookRepository: IssueBookRepository? = null

    override fun getIssueDetails(issueId: Int): IssueBook {
        return issueBookRepository?.findById(issueId)!!.orElseThrow();
    }

    override fun getAllIssuedBooks(): List<IssueBook> {
        return issueBookRepository?.findAll()!!;
    }

    override fun issueTheBook(issueBook: IssueBook): String {
        issueBookRepository?.save(issueBook);
        return "Book issued successfully";
    }

    override fun returnTheBook(issueBook: IssueBook): String {
        issueBookRepository?.save(issueBook);
        return "Book returned successfully";
    }

    override fun getAllIssuedBooksByUser(userId: Int): List<IssueBook> {
        return issueBookRepository?.findAllByUserId(userId)!!;
    }

}