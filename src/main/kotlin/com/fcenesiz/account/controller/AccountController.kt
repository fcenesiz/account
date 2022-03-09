package com.fcenesiz.account.controller

import com.fcenesiz.account.dto.AccountDto
import com.fcenesiz.account.dto.CreateAccountRequest
import com.fcenesiz.account.service.AccountService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/account")
class AccountController(
    val accountService: AccountService
){

    @PostMapping
    fun createAccount(@RequestBody request: CreateAccountRequest): ResponseEntity<AccountDto> =
        ResponseEntity.ok(accountService.createAccount(request))

}