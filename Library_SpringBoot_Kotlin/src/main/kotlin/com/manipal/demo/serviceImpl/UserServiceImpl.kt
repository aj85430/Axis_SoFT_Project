package com.manipal.demo.serviceImpl

import com.manipal.demo.model.User
import com.manipal.demo.repository.UserRepository
import com.manipal.demo.service.IUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
public class UserServiceImpl: IUserService{

    @Autowired
    private val userRepository: UserRepository? = null

    override fun getAllUsers(): List<User> {
        return userRepository?.findAll()!!;
    }

    override fun getUser(userId: Int): User {
        return userRepository?.findById(userId)!!.orElseThrow();
    }

    override fun addUser(user: User): User {
        return userRepository?.save(user)!!;
    }


    override fun deleteUser(userId: Int) {
        userRepository?.deleteById(userId)!!;
    }

    override fun updateUser(user: User): User {
        return userRepository?.save(user)!!;
    }

    override fun getUserByUserName(userName: String): List<User> {
        return userRepository?.findByUserName(userName)!!;
    }


}