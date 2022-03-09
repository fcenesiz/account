package com.fcenesiz.account.dto

import com.fcenesiz.account.model.Customer
import org.springframework.stereotype.Component
import java.util.stream.Collectors

@Component
class CustomerDtoConverter(
    private val converter: CustomerAccountDtoConverter
){


    fun convertToAccountCustomer(from: Customer?): AccountCustomerDto {
        if (from == null) {
            return AccountCustomerDto("", "", "")
        }
        return AccountCustomerDto(
            from.id,
            from.name,
            from.surname
        )
    }

    fun convertToCustomerDto(from: Customer): CustomerDto =
        CustomerDto(
            from.id,
            from.name,
            from.surname,
            from.account.stream().map(converter::convert).collect(Collectors.toSet())
        )

}