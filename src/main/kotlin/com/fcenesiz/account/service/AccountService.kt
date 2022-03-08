package com.fcenesiz.account.service

import com.fcenesiz.account.repository.AccountRepository
import org.springframework.stereotype.Service

@Service
class AccountService(
    private val accountRepository: AccountRepository
)