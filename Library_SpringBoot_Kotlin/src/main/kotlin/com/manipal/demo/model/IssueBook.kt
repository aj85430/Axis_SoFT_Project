package com.manipal.demo.model

import javax.persistence.*


@Entity
@Table(name = "issued")
class IssueBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var issueId = 0
    var userId = 0
    var bookId = 0
    var issueDate: String = ""
    var returnDate: String = ""
    var period = 0
    var fine = 0

    constructor() {}
    constructor(issueId: Int, userId: Int, bookId: Int, issueDate: String, returnDate: String, period: Int, fine: Int) : super() {
        this.issueId = issueId
        this.userId = userId
        this.bookId = bookId
        this.issueDate = issueDate
        this.returnDate = returnDate
        this.period = period
        this.fine = fine
    }
}
