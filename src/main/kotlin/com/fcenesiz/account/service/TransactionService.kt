package com.fcenesiz.account.service

import com.fcenesiz.account.model.Account
import com.fcenesiz.account.model.Transaction
import com.fcenesiz.account.repository.TransactionRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.math.BigDecimal


@Service
class TransactionService(
    // gelişmiş log
    private val logger: Logger = LoggerFactory.getLogger(TransactionService::class.java),
    private val transactionRepository: TransactionRepository
){

    // transactionRepository(JPA), değeri kaydedip bu değeri bize döndürür.
    internal fun initiateMoney(account: Account, amount: BigDecimal) : Transaction =
        transactionRepository.save(Transaction(amount, account))

}