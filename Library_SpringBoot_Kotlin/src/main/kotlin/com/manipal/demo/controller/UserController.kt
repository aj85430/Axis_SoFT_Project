package com.manipal.demo.controller

import com.manipal.demo.model.Book
import com.manipal.demo.model.IssueBook
import com.manipal.demo.model.User
import com.manipal.demo.serviceImpl.BookServiceImpl
import com.manipal.demo.serviceImpl.IssueBookServiceImpl
import com.manipal.demo.serviceImpl.UserServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.temporal.ChronoUnit


@RestController
public class UserController {

    @Autowired
    var userService: UserServiceImpl? = null

    @Autowired
    var bookService: BookServiceImpl? = null

    @Autowired
    var issueBookService: IssueBookServiceImpl? = null

    @GetMapping("/users")
    fun displayUsers(): List<User?>? {
        return userService!!.getAllUsers()
    }

    @GetMapping("/users/{userId}")
    fun displayUserById(@PathVariable userId: Int): User? {
        return userService!!.getUser(userId)
    }

    @PostMapping("/users")
    fun addNewUser(@RequestBody user: User?): ResponseEntity<User?>? {
        val new_User = userService!!.addUser(user!!)
        val headers = HttpHeaders()
        headers.add("response-form", "UserController")
        return ResponseEntity<User?>(new_User, headers, HttpStatus.OK)
    }

    @DeleteMapping("/users/{userId}")
    fun deleteUser(@PathVariable userId: Int) {
        userService!!.deleteUser(userId)
    }

    @PutMapping("/users/{userId}")
    fun updateUser(@PathVariable userId: Int, @RequestBody userDetails: User): ResponseEntity<User>? {
        val user = userService!!.getUser(userId)
        user.userName= userDetails.userName
        user.email= userDetails.email
        val userUpdated = userService!!.updateUser(user)
        return ResponseEntity.ok(userUpdated)
    }


    @GetMapping("/users/name/{userName}")
    fun displayUserByUserName(@PathVariable userName: String?): List<User?>? {
        return userService!!.getUserByUserName(userName!!)
    }

    @GetMapping("books/issued/{userId}")
    fun displayAllIssuedBooksByUserId(@PathVariable userId: Int): List<IssueBook?>? {
        return issueBookService!!.getAllIssuedBooksByUser(userId)
    }

    @PostMapping("/books/issue/{bookId}")
    fun issueTheBook(@PathVariable bookId: Int, @RequestBody issuebook: IssueBook?): String? {
        val book: Book = bookService!!.getBook(bookId)
        val status: String = book.status
        return if (status.equals("Available")) {
            issueBookService!!.issueTheBook(issuebook!!)
            book.status="Not Available"
            bookService!!.updateBook(book)
            "Book Issued Successfully"

        } else {
            "Book is not Available!!"
        }
    }

    @PutMapping("/books/return/{issueId}")
    fun returnBook(@PathVariable issueId: Int, @RequestBody issueDetails: IssueBook): String? {
        val issueBook = issueBookService!!.getIssueDetails(issueId)
        issueBook.returnDate = issueDetails.returnDate
        val bookId:Int = issueBook.bookId
        val book = bookService!!.getBook(bookId)
        book.status= "Available"
        bookService!!.updateBook(book)
        val period = issueBook.period
        val dateIssue = LocalDate.parse(issueBook.issueDate)
        val dateReturn = LocalDate.parse(issueDetails.returnDate)
        val fine: Int
        val noOfDaysBetween = ChronoUnit.DAYS.between(dateIssue, dateReturn).toInt()
        fine = if (noOfDaysBetween > period) {
            (noOfDaysBetween - period) * 10
        } else {
            0
        }
        issueBook.fine = fine
        issueBookService!!.returnTheBook(issueBook)

        return "Book Returned Successfully!!\nTotal Fine is: $fine"
    }


}