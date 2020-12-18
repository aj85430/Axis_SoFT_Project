package com.manipal.demo.repository

import com.manipal.demo.model.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface BookRepository: JpaRepository<Book, Int> {
    abstract fun findByAuthorName(authorName: String): List<Book>
    abstract fun findByBookName(bookName: String): List<Book>?

    @Query("select t from Book t where t.status='Available' ")
    fun findAllAvailableBooks(): List<Book?>?

}