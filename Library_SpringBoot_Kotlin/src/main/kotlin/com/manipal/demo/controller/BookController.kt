package com.manipal.demo.controller

import com.manipal.demo.model.Book
import com.manipal.demo.model.IssueBook
import com.manipal.demo.serviceImpl.BookServiceImpl
import com.manipal.demo.serviceImpl.IssueBookServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
public class BookController {

    @Autowired
    var bookService: BookServiceImpl? = null

    @Autowired
    var issueBookService: IssueBookServiceImpl? = null

    @GetMapping("/books")
    fun displayBooks(): List<Book?>? {
        return bookService!!.getAllBooks()
    }

    @GetMapping("/books/{bookId}")
    fun displayBookById(@PathVariable bookId: Int): Book? {
        return bookService!!.getBook(bookId)
    }

    @PostMapping("/books")
    fun addNewBook(@RequestBody book: Book?): ResponseEntity<Any?>? {
        val new_book = bookService?.addBook(book!!)
        val headers = HttpHeaders()
        headers.add("response-form", "BookController")
        return ResponseEntity<Any?>(new_book, headers, HttpStatus.OK)
    }

    @DeleteMapping("/books/{bookId}")
    fun deleteBook(@PathVariable bookId: Int): String? {
        bookService!!.deleteBook(bookId)
        return "Book Deleted Successfully"
    }

    @GetMapping("/books/author/{author}")
    fun displayBookByAuthor(@PathVariable author: String?): List<Book?>? {
        return bookService!!.getBookByAuthorName(author!!)
    }

    @GetMapping("/books/title/{bookName}")
    fun displayBookByBookName(@PathVariable bookName: String?): List<Book?>? {
        return bookService!!.getBookByBookName(bookName!!)
    }

    @GetMapping("books/issued")
    fun displayAllIssuedBooks(): List<IssueBook?>? {
        return issueBookService!!.getAllIssuedBooks()
    }

    @PutMapping("/books/{bookId}")
    fun updateBook(@PathVariable bookId: Int, @RequestBody bookDetails: Book): ResponseEntity<Book>? {
        val book = bookService!!.getBook(bookId)
        book.bookId=bookDetails.bookId
        book.bookName= bookDetails.bookName
        book.authorName= bookDetails.authorName
        book.price= bookDetails.price
        book.status=bookDetails.status
        val bookUpdated = bookService!!.updateBook(book)
        return ResponseEntity.ok(bookUpdated)
    }


    @GetMapping("books/search/available/")
    fun displayAllAvailableBooks(): List<Book?>? {
        return bookService!!.getAllAvailableBooks()
    }


}