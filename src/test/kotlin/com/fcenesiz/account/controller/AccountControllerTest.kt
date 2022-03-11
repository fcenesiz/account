package com.fcenesiz.account.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fcenesiz.account.dto.AccountDtoConverter
import com.fcenesiz.account.dto.CreateAccountRequest
import com.fcenesiz.account.model.Customer
import com.fcenesiz.account.repository.AccountRepository
import com.fcenesiz.account.repository.CustomerRepository
import com.fcenesiz.account.service.AccountService
import com.fcenesiz.account.service.CustomerService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import java.math.BigDecimal

// integration test
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = ["server-port=0", "command.line.enable=false"]
)
@DirtiesContext
internal class AccountControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
     val accountRepository: AccountRepository,
     val customerRepository: CustomerRepository,
     val customerService: CustomerService,
     val accountDtoConverter: AccountDtoConverter,
     val objectMapper: ObjectMapper
) {

    private final val version: String = "v1"
    val baseAccountUrl: String = "/$version/account"
    val baseCustomerUrl: String = "/$version/customer"

    private val accountService: AccountService = AccountService(accountRepository, customerService, accountDtoConverter)

    init {
        objectMapper.registerModule(JavaTimeModule())
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
    }

    @Test
    fun `should return account dto and create an account when customer id is exist`() {
        // given
        val customer: Customer = customerRepository.save(Customer("Hüseyin", "Beller"))
        val request = CreateAccountRequest(customer.id!!, BigDecimal(50))

        // when
        val performPost = mockMvc.post(baseAccountUrl) {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }

        // then
        performPost
            .andDo { print() }
            .andExpect {
                status { is2xxSuccessful() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.id"){ exists()}
                jsonPath("$.balance"){value(50)}
                jsonPath("$.customer.id"){value(customer.id)}
                jsonPath("$.customer.name"){value("Hüseyin")}
                jsonPath("$.customer.surname"){value("Beller")}
                jsonPath("$.transactions"){ isNotEmpty() }
            }




    }

}