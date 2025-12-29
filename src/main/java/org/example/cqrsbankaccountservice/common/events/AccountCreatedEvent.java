package org.example.cqrsbankaccountservice.common.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.cqrsbankaccountservice.common.enums.AccountStatus;

@Getter
@AllArgsConstructor
public class AccountCreatedEvent {
    private String id;
    private double initialBalance;
    private String currency;
    private AccountStatus status;
}
