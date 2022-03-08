package com.fcenesiz.account.repository

import com.fcenesiz.account.model.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository :JpaRepository<Account, String>{
}