package org.example.cqrsbankaccountservice.commands.commands;

import lombok.Getter;

public class CreateAccountCommand extends BaseCommand{
    @Getter private double initialBalance;
    @Getter private String currency;
    public CreateAccountCommand(Object id) {
        super(id);
        this.initialBalance = initialBalance;
        this.currency = currency;
    }

}