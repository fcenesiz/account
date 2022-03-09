package com.fcenesiz.account.service

import com.fcenesiz.account.dto.CustomerDto
import com.fcenesiz.account.dto.CustomerDtoConverter
import com.fcenesiz.account.exception.CustomerNotFoundException
import com.fcenesiz.account.model.Customer
import com.fcenesiz.account.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository,
    private val converter: CustomerDtoConverter
) {

    // findById Optional bir fonksiyon, yani null olan durumlarda bizi kurtarır.
    internal fun findCustomerById(id: String): Customer =
        customerRepository.findById(id).orElseThrow { CustomerNotFoundException("Customer could not found by id:$id") }

    fun getCustomerById(customerId: String): CustomerDto =
        converter.convertToCustomerDto(findCustomerById(customerId))

}