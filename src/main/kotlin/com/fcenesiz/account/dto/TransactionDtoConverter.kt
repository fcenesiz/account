package com.fcenesiz.account.dto

import com.fcenesiz.account.model.Transaction
import org.springframework.stereotype.Component

@Component
class TransactionDtoConverter() {
    fun convert(from: Transaction): TransactionDto =
        TransactionDto(
            from.id,
            from.transactionType,
            from.amount,
            from.transactionDate
        )
}