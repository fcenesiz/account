package com.fcenesiz.account.service

import com.fcenesiz.account.dto.AccountDto
import com.fcenesiz.account.dto.AccountDtoConverter
import com.fcenesiz.account.dto.CreateAccountRequest
import com.fcenesiz.account.model.Account
import com.fcenesiz.account.model.Transaction
import com.fcenesiz.account.repository.AccountRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.Clock
import java.time.Instant
import java.time.LocalDateTime

@Service
class AccountService(
    private val accountRepository: AccountRepository,
    private val customerService: CustomerService,
    private val transactionService: TransactionService,
    private val converter: AccountDtoConverter,
    private val clock: Clock
){

    fun createAccount(createAccountRequest: CreateAccountRequest):AccountDto{
        val customer = customerService.findCustomerById(createAccountRequest.customerId)

        val account = Account(
            balance = createAccountRequest.initialCredit,
            creationDate = LocalDateTime.now(),
            customer = customer
        )

        if(createAccountRequest.initialCredit.compareTo(BigDecimal.ZERO) > 0){
            val transaction = transactionService.initiateMoney(account, createAccountRequest.initialCredit)
            account.transactions.add(transaction)
        }
        return converter.convert(accountRepository.save(account))
    }

    fun getLocalTimeNow(): LocalDateTime{
        val instant = clock.instant()
        return LocalDateTime.ofInstant(instant, Clock.systemDefaultZone().zone)
    }

}