package com.fcenesiz.account.dto

import com.fcenesiz.account.model.Account
import org.springframework.stereotype.Component
import java.util.*
import java.util.stream.Collectors

@Component
class CustomerAccountDtoConverter(
    private val converter : TransactionDtoConverter
) {

    fun convert(from: Account) : CustomerAccountDto =
        CustomerAccountDto(
            Objects.requireNonNull(from.id),
            from.balance,
            from.transactions.stream().map(converter::convert).collect(Collectors.toSet()),
            from.creationDate
        )

}