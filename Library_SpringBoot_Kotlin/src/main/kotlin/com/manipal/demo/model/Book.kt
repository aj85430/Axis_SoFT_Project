package com.manipal.demo.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="books")
public class Book {

    @Id
    var bookId:Int=0
    var bookName:String=""
    var authorName:String=""
    var price:Int=0
    var status:String=""

    constructor(){}
    constructor(bookId: Int, bookName: String, authorName: String, price: Int, status: String) {
        this.bookId = bookId
        this.bookName = bookName
        this.authorName = authorName
        this.price = price
        this.status = status
    }
}