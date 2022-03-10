package com.fcenesiz.account.exception

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@RestControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {

        val errors: HashMap<String, String>  = HashMap()
        ex.bindingResult.allErrors.forEach {
            val fieldName : String = (it as FieldError).field
            val errorMessage : String? = it.defaultMessage
            errors.put(fieldName, errorMessage!!)
        }

        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(CustomerNotFoundException::class)
    fun customerNotFoundExceptionHandler(exception: CustomerNotFoundException): ResponseEntity<String> =
        ResponseEntity(exception.message, HttpStatus.NOT_FOUND)

}