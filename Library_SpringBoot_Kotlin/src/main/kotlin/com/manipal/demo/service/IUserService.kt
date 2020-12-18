package com.manipal.demo.service

import com.manipal.demo.model.User

interface IUserService {

    fun getAllUsers():List<User>

    fun getUser(userId: Int):User

    fun addUser(user: User): User

    fun deleteUser(userId: Int)

    fun updateUser(user: User): User

    fun getUserByUserName(userName: String): List<User>


}