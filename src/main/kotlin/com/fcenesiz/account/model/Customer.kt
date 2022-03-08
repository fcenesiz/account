package com.fcenesiz.account.model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
data class Customer (
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String?,

    val name: String,
    val surname: String,

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    val account: Set<Account>?
)