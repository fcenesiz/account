package com.fcenesiz.account

import com.fcenesiz.account.model.Account
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.math.BigDecimal
import java.time.LocalDateTime

@SpringBootApplication
class AccountApplication : CommandLineRunner{
	override fun run(vararg args: String?) {
		val a = Account("a", BigDecimal.ONE, LocalDateTime.now(), null, emptySet())
		val b = Account("b", BigDecimal.ONE, LocalDateTime.now(), null, emptySet())
	}

}

fun main(args: Array<String>) {
	runApplication<AccountApplication>(*args)
}
