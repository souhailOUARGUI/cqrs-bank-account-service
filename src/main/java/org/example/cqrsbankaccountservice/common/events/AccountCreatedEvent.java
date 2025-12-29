package org.example.cqrsbankaccountservice.common.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.cqrsbankaccountservice.common.enums.AccountStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreatedEvent {
    private String id;
    private double initialBalance;
    private String currency;
    private AccountStatus status;
}
