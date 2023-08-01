package com.codingwitherik.library.interceptors

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ControllerExceptionHandler {
    @ExceptionHandler(NoSuchElementException::class)
    fun handleNoSuchElementException(e: NoSuchElementException): ResponseEntity<ErrorDto> {
        return ResponseEntity(ErrorDto(e.message.orEmpty()), HttpStatus.BAD_REQUEST)
    }
}

class ErrorDto(val message: String)