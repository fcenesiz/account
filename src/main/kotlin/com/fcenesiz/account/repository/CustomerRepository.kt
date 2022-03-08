package com.fcenesiz.account.repository

import com.fcenesiz.account.model.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<Customer, String>