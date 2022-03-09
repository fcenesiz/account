package com.fcenesiz.account

import com.fcenesiz.account.model.Account
import com.fcenesiz.account.model.Customer
import com.fcenesiz.account.repository.CustomerRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.math.BigDecimal
import java.time.LocalDateTime

@SpringBootApplication
class AccountApplication(
	val customerRepository : CustomerRepository
): CommandLineRunner{

	override fun run(vararg args: String?) {
		val customer = customerRepository.save(Customer("", "Hüseyin", "Beller", HashSet()))
		println(customer)
	}
}

fun main(args: Array<String>) {
	runApplication<AccountApplication>(*args)
}
