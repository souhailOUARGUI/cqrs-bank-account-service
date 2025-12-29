package org.example.cqrsbankaccountservice.commands.aggregates;


import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.example.cqrsbankaccountservice.commands.commands.CreateAccountCommand;
import org.example.cqrsbankaccountservice.common.enums.AccountStatus;
import org.example.cqrsbankaccountservice.common.events.AccountCreatedEvent;

@Aggregate
@NoArgsConstructor // Requis par Axon
@Slf4j
public class AccountAggregate {
    @AggregateIdentifier
    private String accountId;
    private double balance;
    private String currency;
    private AccountStatus status;

    // 1. Command Handler
    @CommandHandler
    public AccountAggregate(CreateAccountCommand command) {
        log.info("CreateAccountCommand received");
        // Règle métier : solde initial positif [12]
        if(command.getInitialBalance() < 0) {
            throw new RuntimeException("Initial balance cannot be negative");
        }

        // Publication de l'événement
        AggregateLifecycle.apply(new AccountCreatedEvent(
                (String) command.getId(),
                command.getInitialBalance(),
                command.getCurrency(),
                AccountStatus.CREATED
        ));
    }

    // 2. Event Sourcing Handler
    @EventSourcingHandler
    public void on(AccountCreatedEvent event) {
        log.info("AccountCreatedEvent occured");
        this.accountId = event.getId().toString();
        this.balance = event.getInitialBalance();
        this.currency = event.getCurrency();
        this.status = event.getStatus();
    }

}
