package com.fcenesiz.account.controller

import com.fcenesiz.account.dto.CustomerDto
import com.fcenesiz.account.service.CustomerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/customer")
class CustomerController(val customerService: CustomerService){

    @GetMapping("/{customerId}")
    fun getCustomerById(@PathVariable customerId: String): ResponseEntity<CustomerDto> =
        ResponseEntity.ok(customerService.getCustomerById(customerId))

}