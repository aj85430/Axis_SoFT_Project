package com.manipal.demo.testService

import com.manipal.demo.model.IssueBook
import com.manipal.demo.repository.IssueBookRepository
import com.manipal.demo.serviceImpl.IssueBookServiceImpl
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.util.*
import org.junit.jupiter.api.Assertions.*


@SpringBootTest
internal class IssueBookServiceTest {
    @Autowired
    var issueBookService: IssueBookServiceImpl? = null

    @MockBean
    private val issueBookRepository: IssueBookRepository? = null

    @Test
    fun issueTheBookTest() {
        val issueBook = IssueBook(1, 50001, 1001, "2020-11-10", "", 3, 0)
        issueBookService!!.issueTheBook(issueBook)
        verify(issueBookRepository, times(1))!!.save(issueBook)
    }

    @Test
    fun returnTheBookTest() {
        val issueBook = IssueBook(1, 50001, 1001, "2020-11-10", "2020-15-10", 3, 20)
        issueBookService!!.returnTheBook(issueBook)
        verify(issueBookRepository, times(1))!!.save(issueBook)
    }

    @Test
    fun getIssueDetailsTest() {
        val issueId = 10
        val issueBook = IssueBook(1, 50001, 1001, "2020-11-10", "2020-15-10", 3, 20)
        `when`(issueBookRepository!!.findById(issueId)).thenReturn(Optional.of(issueBook))
        assertEquals(issueBook, issueBookService!!.getIssueDetails(issueId))
    }


    @Test
    fun getAllIssuedBooksTest() {
        val bookMockList: MutableList<IssueBook> = ArrayList()
        bookMockList.add(IssueBook(1, 50001, 1001, "2020-11-10", "2020-15-10", 3, 20))
        bookMockList.add(IssueBook(2, 50002, 1002, "2020-11-12", "2020-11-14", 1, 10))
        `when`(issueBookRepository!!.findAll()).thenReturn(bookMockList)
        assertEquals(2, issueBookService!!.getAllIssuedBooks().size)
    }


    @Test
    fun getAllIssuedBooksByUserTest() {
        val userId = 50001
        val bookMockList: MutableList<IssueBook> = ArrayList()
        bookMockList.add(IssueBook(1, 50001, 1001, "2020-11-10", "2020-15-10", 3, 20))
        bookMockList.add(IssueBook(2, 50001, 1002, "2020-11-12", "2020-11-14", 1, 10))
        `when`(issueBookRepository!!.findAllByUserId(userId)).thenReturn(bookMockList)
        assertEquals(2, issueBookService!!.getAllIssuedBooksByUser(userId).size)
    }

}
