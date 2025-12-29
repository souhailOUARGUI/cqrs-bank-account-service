package org.example.cqrsbankaccountservice.query.controllers;


import org.example.cqrsbankaccountservice.query.entities.Account;
import org.example.cqrsbankaccountservice.query.repositories.AccountRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/query/accounts")
public class AccountQueryController {
    private final AccountRepository accountRepository;

    public AccountQueryController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Account>> accountList() {
        List<Account> accounts = accountRepository.findAll();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable String id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }
}
