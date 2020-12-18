package com.manipal.demo.testService

import com.manipal.demo.model.User
import com.manipal.demo.repository.UserRepository
import com.manipal.demo.serviceImpl.UserServiceImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import java.util.*


@SpringBootTest
internal class UserServiceTest {
    @Autowired
    var userService: UserServiceImpl? = null

    @MockBean
    private val userRepository: UserRepository? = null

    @Test
    fun allUsersTest() {
            val userMockList: MutableList<User> = ArrayList<User>()
            userMockList.add(User(50001, "Aman Jaiswal", "abc@gmail.com"))
            userMockList.add(User(50002, "Gourav Sharma", "xyz@gmail.com"))
            `when`(userRepository!!.findAll()).thenReturn(userMockList)
            assertEquals(2, userService!!.getAllUsers().size)
        }

    @Test
    fun addUserTest() {
        val user = User(50001, "Aman Jaiswal", "abc@gmail.com")
        `when`<Any>(userRepository!!.save(user)).thenReturn(user)
        assertEquals(user, userService!!.addUser(user))
    }

    @Test
    fun userByUserNameTest()
        {
            val userMockList: MutableList<User> = ArrayList<User>()
            userMockList.add(User(50001, "Aman Jaiswal", "abc@gmail.com"))
            userMockList.add(User(50002, "Gourav Sharma", "xyz@gmail.com"))
            val name = "Aman Jaiswal"
            `when`(userRepository!!.findByUserName(name)).thenReturn(userMockList)
            assertEquals(name, userService!!.getUserByUserName(name)[0].userName)
        }

    @Test
    fun getUserTest()
        {
            val user = User(50001, "Aman Jaiswal", "abc@gmail.com")
            val id = 50001
            `when`(userRepository!!.findById(id)).thenReturn(Optional.of(user))
            assertEquals(user, userService!!.getUser(id))
        }

    @Test
    fun deleteUserTest() {
        val user = User(50001, "Aman Jaiswal", "abc@gmail.com")
        userService!!.deleteUser(user.userId)
        verify(userRepository, times(1))!!.deleteById(user.userId)
    }
}
