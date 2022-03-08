package com.fcenesiz.account.dto

import com.fcenesiz.account.model.Transaction
import java.math.BigDecimal
import java.time.LocalDateTime

class CustomerAccountDto(
    val id: String,
    var balance: BigDecimal? = BigDecimal.ZERO,
    val transactions: Set<TransactionDto>?,
    val creationDate: LocalDateTime
)