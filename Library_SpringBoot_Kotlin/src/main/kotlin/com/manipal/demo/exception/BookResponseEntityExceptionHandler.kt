package com.manipal.demo.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
class BookResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(BookNotFoundException::class)
    fun toDoNotFound(exception: BookNotFoundException): ResponseEntity<ExceptionResponse?> {
        val exceptionResponse = ExceptionResponse(exception.message, LocalDateTime.now())
        return ResponseEntity<ExceptionResponse?>(exceptionResponse, HttpStatus.NOT_FOUND)
    }
}


