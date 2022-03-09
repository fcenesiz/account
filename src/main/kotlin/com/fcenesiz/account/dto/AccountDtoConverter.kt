package com.fcenesiz.account.dto

import com.fcenesiz.account.model.Account
import org.springframework.stereotype.Component
import java.util.stream.Collectors

@Component
class AccountDtoConverter(
    private val customerDtoConverter: CustomerDtoConverter,
    private val transactionDtoConverter: TransactionDtoConverter
) {

    fun convert(from: Account): AccountDto =
        AccountDto(
            from.id,
            from.balance,
            from.creationDate,
            customerDtoConverter.convertToAccountCustomer(from.customer),
            requireNotNull(from.transactions).stream().map(transactionDtoConverter::convert).collect(Collectors.toSet())
        )
}