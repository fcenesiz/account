package com.fcenesiz.account.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.NOT_FOUND)
class CustomerNotFoundException(message: String?) : RuntimeException(message) {
}