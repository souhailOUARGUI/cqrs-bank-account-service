package org.example.cqrsbankaccountservice.common.events;

import lombok.Getter;
import org.example.cqrsbankaccountservice.commands.commands.BaseCommand;
import org.example.cqrsbankaccountservice.common.enums.AccountStatus;

public class AccountCreatedEvent  extends BaseCommand {
    @Getter
    private double initialBalance;
    @Getter private String currency;
    @Getter private AccountStatus status;


    public AccountCreatedEvent(String id, double initialBalance, String currency, AccountStatus status) {
        super(id);
        this.initialBalance = initialBalance;
        this.currency = currency;
        this.status = status;
    }
}
