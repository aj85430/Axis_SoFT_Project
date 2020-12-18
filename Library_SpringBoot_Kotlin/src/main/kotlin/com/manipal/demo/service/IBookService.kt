package com.manipal.demo.service

import com.manipal.demo.model.Book

interface IBookService {

    fun getAllBooks(): List<Book>

    fun getBook(bookId: Int): Book

    fun addBook(book: Book): Book

    fun deleteBook(bookId: Int): String

    fun updateBook(book: Book): Book

    fun getBookByAuthorName(author: String): List<Book>

    fun getBookByBookName(bookName: String): List<Book>

    fun getAllAvailableBooks(): List<Book?>?

}

