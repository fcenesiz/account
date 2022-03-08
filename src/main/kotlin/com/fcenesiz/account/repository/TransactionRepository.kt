package com.fcenesiz.account.repository

import com.fcenesiz.account.model.Account
import com.fcenesiz.account.model.Transaction
import org.springframework.data.jpa.repository.JpaRepository

interface TransactionRepository : JpaRepository<Transaction, String>