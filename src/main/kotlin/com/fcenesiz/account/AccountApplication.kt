package com.fcenesiz.account

import com.fcenesiz.account.model.Account
import com.fcenesiz.account.model.Customer
import com.fcenesiz.account.repository.CustomerRepository
import org.apache.logging.log4j.util.Supplier
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.time.Clock
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.HashSet

// @Component ile uygulama ayağa kalkarken çalıştırmak istediğimiz şeyleri ekleriz.

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
