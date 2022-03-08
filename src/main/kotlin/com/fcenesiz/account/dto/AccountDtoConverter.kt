package com.fcenesiz.account.dto

import com.fcenesiz.account.model.Account
import java.util.stream.Collectors

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
            requireNotNull(from.transaction).stream().map(transactionDtoConverter::convert).collect(Collectors.toSet())
        )
}