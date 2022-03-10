package com.fcenesiz.account.dto

import java.math.BigDecimal
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

class CreateAccountRequest(

    @field:NotBlank() // boş olmasını istemiyoruz, bilgisayarın diline göre default mesaj üretecektir. Yada kendimiz yazabiliriz.
    val customerId: String,
    @field:Min(0) // negatif olamaz, bilgisayarın diline göre default mesaj üretecektir. Yada kendimiz yazabiliriz.
    val initialCredit: BigDecimal
)