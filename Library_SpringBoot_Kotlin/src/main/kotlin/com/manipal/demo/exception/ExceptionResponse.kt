package com.manipal.demo.exception

import java.time.LocalDateTime


class ExceptionResponse {
    var message: String? = null
    var timestamp: LocalDateTime? = null

    constructor() {}
    constructor(message: String?, timestamp: LocalDateTime?) : super() {
        this.message = message
        this.timestamp = timestamp
    }
}