package com.manipal.demo.testController

import com.fasterxml.jackson.databind.ObjectMapper
import com.manipal.demo.controller.UserController
import com.manipal.demo.model.User
import com.manipal.demo.serviceImpl.BookServiceImpl
import com.manipal.demo.serviceImpl.IssueBookServiceImpl
import com.manipal.demo.serviceImpl.UserServiceImpl
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
@WebMvcTest(UserController::class)
internal class UserControllerTest {
    @Autowired
    private val mvc: MockMvc? = null

    @MockBean
    private val userService: UserServiceImpl? = null

    @MockBean
    private val bookService: BookServiceImpl? = null

    @MockBean
    private val issueBookService: IssueBookServiceImpl? = null

    @Test
    @Throws(Exception::class)
    fun displayUsersTest() {
        mvc!!.perform(get("/users").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
    }

    @Test
    @Throws(Exception::class)
    fun displayUserByIdTest() {
        mvc!!.perform(get("/users/50001").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
    }

    @Test
    @Throws(Exception::class)
    fun displayUserByUserNameTest() {
        mvc!!.perform(get("/users/name/Aman Jaiswal").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
    }

    @Test
    @Throws(Exception::class)
    fun addNewUserTest() {
        val objectMapper = ObjectMapper()
        val user = User(50001, "Aman Jaiswal", "abc@gmail.com")
        mvc!!.perform(post("/users")
                .content(objectMapper.writeValueAsString(user))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun deleteUserTest() {
        mvc!!.perform(delete("/users/50001").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun displayAllIssuedBooksByUserIdTest() {
        mvc!!.perform(get("/books/issued/50001").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
    }

}
