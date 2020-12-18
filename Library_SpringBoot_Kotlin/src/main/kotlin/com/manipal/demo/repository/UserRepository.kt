package com.manipal.demo.repository

import com.manipal.demo.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository:JpaRepository<User, Int> {
    abstract fun findByUserName(userName: String): List<User>?

}