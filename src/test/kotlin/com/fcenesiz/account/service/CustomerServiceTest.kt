package com.fcenesiz.account.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fcenesiz.account.dto.CustomerDto
import com.fcenesiz.account.dto.CustomerDtoConverter
import com.fcenesiz.account.exception.CustomerNotFoundException
import com.fcenesiz.account.model.Account
import com.fcenesiz.account.model.Customer
import com.fcenesiz.account.repository.CustomerRepository
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.TestInstance.*
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.mockingDetails
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

/*
    private ve static methodların testi yazılmaz
 */
@SpringBootTest
internal class CustomerServiceTest {
    private final val version: String = "v1"
    val baseAccountUrl: String = "/$version/account"
    val baseCustomerUrl: String = "/$version/customer"


    private lateinit var service: CustomerService
    private val customerRepository= mock(CustomerRepository::class.java)
    private val customerDtoConverter = mock(CustomerDtoConverter::class.java)

    init{
        service = CustomerService(customerRepository, customerDtoConverter)
    }


    @Nested
    @DisplayName("GET /v1/customer")
    @TestInstance(Lifecycle.PER_CLASS)
    inner class AddAccount {

        @Test
        fun `should return the customer when customer id exist`() {
            // given
            val customer = Customer("1234", "Hüseyin", "Beller", mutableSetOf())

            // when/then
            Mockito.`when`(customerRepository.findById(customer.id!!))
                .thenReturn(Optional.of(customer))

            val result = service.findCustomerById(customer.id!!)

            assertEquals(result, customer)

        }

        @Test
        fun `should return NOT FOUND with findCustomerById when customer id does not exist`() {
            // given
            val customerId = "1234"

            // when/then
            Mockito.`when`(customerRepository.findById(customerId))
                .thenReturn(Optional.empty())

            assertThrows<CustomerNotFoundException> { service.findCustomerById(customerId) }

        }

        @Test
        fun `should return the customer dto when customer id exist`() {
            // given
            val customer = Customer("1234", "Hüseyin", "Beller", mutableSetOf())
            val customerDto = CustomerDto("1234", "Hüseyin", "Beller", mutableSetOf())
            // when/then
            Mockito.`when`(customerRepository.findById(customer.id!!))
                .thenReturn(Optional.of(customer))
            Mockito.`when`(customerDtoConverter.convertToCustomerDto(customer))
                .thenReturn(customerDto)

            val result = service.getCustomerById(customer.id!!)

            assertEquals(result, customerDto)

        }

        @Test
        fun `should return NOT FOUND with getCustomerById when customer id does not exist`() {
            // given
            val customerId = "1234"

            // when/then
            Mockito.`when`(customerRepository.findById(customerId))
                .thenReturn(Optional.empty())

            assertThrows<CustomerNotFoundException> { service.getCustomerById(customerId) }

        }

    }

}