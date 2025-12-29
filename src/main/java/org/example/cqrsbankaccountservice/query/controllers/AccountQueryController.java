package org.example.cqrsbankaccountservice.query.controllers;


import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.example.cqrsbankaccountservice.query.entities.Account;
import org.example.cqrsbankaccountservice.query.queries.GetAllAccountsQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/query/accounts")
public class AccountQueryController {
    private final QueryGateway queryGateway; // Bus de Query [20]

    public AccountQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping("/all")
    public CompletableFuture<List<Account>> accountList() {
        // Dispatch de la query vers le bus [21]
        return queryGateway.query(
                new GetAllAccountsQuery(),
                ResponseTypes.multipleInstancesOf(Account.class)
        );
    }

}
