package com.fcenesiz.account.model

import org.hibernate.Hibernate
import org.hibernate.annotations.GenericGenerator
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Account(

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = "",

    val balance: BigDecimal? = BigDecimal.ZERO,

    val creationDate: LocalDateTime,
    // cascade.all, kayıt işlemini yap demek
    @ManyToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "customer_id", nullable = false)
    val customer: Customer?,

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val transactions: MutableSet<Transaction> = HashSet()
) {

    constructor(customer: Customer, balance: BigDecimal, creationDate: LocalDateTime) :
            this("", balance, creationDate, customer)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Account

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , balance = $balance , creationDate = $creationDate )"
    }

}