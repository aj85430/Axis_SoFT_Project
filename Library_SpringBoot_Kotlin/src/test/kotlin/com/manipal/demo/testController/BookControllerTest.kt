package com.manipal.demo.testController

import com.fasterxml.jackson.databind.ObjectMapper
import com.manipal.demo.controller.BookController
import com.manipal.demo.model.Book
import com.manipal.demo.serviceImpl.BookServiceImpl
import com.manipal.demo.serviceImpl.IssueBookServiceImpl
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest(BookController::class)
class BookControllerTest {
    @Autowired
    private val mvc: MockMvc? = null

    @MockBean
    private val bookService: BookServiceImpl? = null

    @MockBean
    private val issueBookService: IssueBookServiceImpl? = null

    @Test
    @Throws(Exception::class)
    fun displayBooksTest() {
        mvc!!.perform(get("/books").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
    }

    @Test
    @Throws(Exception::class)
    fun displayBookByIdTest() {
        mvc!!.perform(get("/books/1001").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
    }

    @Test
    @Throws(Exception::class)
    fun displayBookByBookNameTest() {
        mvc!!.perform(get("/books/title/Principia").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
    }

    @Test
    @Throws(Exception::class)
    fun addNewBookTest() {
        val objectMapper = ObjectMapper()
        val book = Book(1001, "War and Peace", "Mike Ryan", 500, "Available")
        mvc!!.perform(post("/books")
                .content(objectMapper.writeValueAsString(book))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
    }

    /*@Test
    @Throws(java.lang.Exception::class)
    fun displayAllAvailableBooksTest() {
        mvc!!.perform(get("/books/search/available").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
    }*/

    @Test
    @Throws(java.lang.Exception::class)
    fun deleteBookTest() {
        mvc!!.perform(delete("/books/1001").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
    }


    @Test
    @Throws(java.lang.Exception::class)
    fun displayBookByAuthorNameTest() {
        mvc!!.perform(get("/books/author/Gulzar").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun displayAllIssuedBooksTest() {
        mvc!!.perform(get("/books/issued").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
    }

}
