package com.manipal.demo.testService

import com.manipal.demo.model.Book
import com.manipal.demo.repository.BookRepository
import com.manipal.demo.serviceImpl.BookServiceImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.util.*


@SpringBootTest
internal class BookServiceTest {

    @Autowired
    var bookService: BookServiceImpl? = null

    @MockBean
    private val bookRepository: BookRepository? = null

   /*@Before
    fun init() {
        MockitoAnnotations.initMocks(this)
    }*/

    @Test
    fun allBooksTest()
         {
            val bookMockList: MutableList<Book> = ArrayList<Book>()
            bookMockList.add(Book(1001, "War and Peace", "Mike Ryan", 500, "Available"))
            bookMockList.add(Book(1002, "Game Of Thrones", "George Martin", 1500, "Available"))
            bookMockList.add(Book(1003, "Love Physics", "Lokie Sams", 300, "Available"))
            `when`(bookRepository!!.findAll()).thenReturn(bookMockList)
            assertEquals(3, bookService!!.getAllBooks().size)
        }

    @Test
    fun addBookTest() {
        val book = Book(1005, "Kotlin", "Luke Garry", 180, "Available")
        `when`<Any>(bookRepository!!.save(book)).thenReturn(book)
        assertEquals(book, bookService!!.addBook(book))
    }

    @Test
    fun getBookTest()
    {
            val book = Book(1001, "War and Peace", "Mike Ryan", 500, "Available")
            val id = 1001
            `when`(bookRepository!!.findById(id)).thenReturn(Optional.of(book))
            assertEquals(book, bookService!!.getBook(id))
    }

    /*@Test
   void updateBookTest() {
       Book book = new Book(1001,"War and Peace","Mike Ryan", 500, "Available");
       bookService.updateBook(book);
       verify(bookRepository, times(1)).save(book);
   }
   */
    @Test
    fun bookByBookNameTest()
        {
            val bookMockList: MutableList<Book> = ArrayList<Book>()
            bookMockList.add(Book(1001, "War and Peace", "Mike Ryan", 500, "Available"))
            val name = "War and Peace"
            `when`(bookRepository!!.findByBookName(name)).thenReturn(bookMockList)
            assertEquals(name, bookService!!.getBookByBookName(name)[0].bookName)
        }

    @Test
    fun bookByAuthorTest()
        {
            val bookMockList: MutableList<Book> = ArrayList<Book>()
            bookMockList.add(Book(1001, "War and Peace", "Mike Ryan", 500, "Available"))
            bookMockList.add(Book(1002, "Love Physics", "Mike Ryan", 300, "Available"))
            val author = "Mike Ryan"
            `when`(bookRepository!!.findByAuthorName(author)).thenReturn(bookMockList)
            assertEquals(2, bookService!!.getBookByAuthorName(author).size)
        }

    @Test
    fun getAllAvailableBooksTest() {
        val bookMockList: MutableList<Book?> = ArrayList()
        bookMockList.add(Book(1001, "War and Peace", "Mike Ryan", 500, "Available"))
        bookMockList.add(Book(1002, "Love Physics", "Mike Ryan", 300, "Available"))
        bookMockList.add(Book(1003, "MindHunter", "George bell", 400, "Available"))
        `when`(bookRepository!!.findAllAvailableBooks()).thenReturn(bookMockList)
        assertEquals(3, bookService!!.getAllAvailableBooks()!!.size)
    }


}
