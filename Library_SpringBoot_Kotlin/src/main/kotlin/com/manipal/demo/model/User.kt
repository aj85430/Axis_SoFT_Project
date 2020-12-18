package com.manipal.demo.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
class User {
    @Id
    var userId = 0
    var userName: String = ""
    var email: String = ""

    constructor() {}
    constructor(userId: Int, userName: String, email: String) : super() {
        this.userId = userId
        this.userName = userName
        this.email = email
    }
}
