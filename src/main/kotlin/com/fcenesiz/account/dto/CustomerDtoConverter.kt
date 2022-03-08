package com.fcenesiz.account.dto

import com.fcenesiz.account.model.Customer
import org.springframework.stereotype.Component

@Component
class CustomerDtoConverter {


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


}