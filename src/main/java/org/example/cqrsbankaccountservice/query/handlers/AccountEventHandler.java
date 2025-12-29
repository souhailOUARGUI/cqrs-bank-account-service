package org.example.cqrsbankaccountservice.query.handlers;


import lombok.extern.slf4j.Slf4j;
import org.example.cqrsbankaccountservice.common.events.AccountCreatedEvent;
import org.example.cqrsbankaccountservice.query.entities.Account;
import org.example.cqrsbankaccountservice.query.repositories.AccountRepository;
import org.springframework.stereotype.Service;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountEventHandler {
    private final AccountRepository accountRepository;

    public AccountEventHandler(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // Réception de l'événement et sauvegarde dans la base SQL
    @EventHandler
    public void on(AccountCreatedEvent event) {
        log.info("AccountCreatedEvent received in Query Side");
        Account account = Account.builder()
                .id((String) event.getId())
                .balance(event.getInitialBalance())
                .currency(event.getCurrency())
                .status(event.getStatus())
                .build();
        accountRepository.save(account);
    }
}
