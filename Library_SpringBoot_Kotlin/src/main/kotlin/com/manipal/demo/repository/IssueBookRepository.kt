package com.manipal.demo.repository

import com.manipal.demo.model.IssueBook
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IssueBookRepository:JpaRepository<IssueBook, Int> {
    abstract fun findAllByUserId(userId: Int): List<IssueBook>


}