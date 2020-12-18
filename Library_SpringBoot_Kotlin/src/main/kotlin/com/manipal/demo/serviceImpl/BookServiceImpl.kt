package com.manipal.demo.serviceImpl

import com.manipal.demo.exception.BookNotFoundException
import com.manipal.demo.model.Book
import com.manipal.demo.repository.BookRepository
import com.manipal.demo.service.IBookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
public class BookServiceImpl: IBookService {

    @Autowired
    private val bookRepository: BookRepository? = null


    override fun getAllBooks(): List<Book> {
        return bookRepository?.findAll()!!;

    }

    override fun getBook(bookId: Int): Book {
        return bookRepository?.findById(bookId)!!.orElseThrow({ BookNotFoundException("Book details not found for id $bookId") })

    }

    override fun addBook(book: Book): Book {
           return bookRepository?.save(book)!!
    }

    override fun deleteBook(bookId: Int): String {
        val bookDetails = bookRepository?.findById(bookId)!!.orElseThrow({ BookNotFoundException("Book details not found for id $bookId") })
        bookDetails.status="Not Available"
        bookRepository.save(bookDetails)
        return "Book Deleted Successfully!"

    }

    override fun updateBook(book: Book): Book {
       var id= book.bookId
       val bookDetails = bookRepository?.findById(id)!!.orElseThrow({ BookNotFoundException("Book details not found for id $(book.bookId)") })
        return bookRepository.save(bookDetails)


    }

    override fun getBookByAuthorName(authorName: String): List<Book> {
        return bookRepository?.findByAuthorName(authorName)!!;
    }

    override fun getBookByBookName(bookName: String): List<Book> {
        return bookRepository?.findByBookName(bookName)!!

    }

    override fun getAllAvailableBooks(): List<Book?>? {
        return bookRepository?.findAllAvailableBooks()
    }

}