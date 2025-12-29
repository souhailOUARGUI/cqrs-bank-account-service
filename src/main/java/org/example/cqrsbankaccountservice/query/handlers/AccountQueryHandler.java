package org.example.cqrsbankaccountservice.query.handlers;
import org.axonframework.queryhandling.QueryHandler;
import org.example.cqrsbankaccountservice.query.entities.Account;
import org.example.cqrsbankaccountservice.query.queries.GetAllAccountsQuery;
import org.example.cqrsbankaccountservice.query.repositories.AccountRepository;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class AccountQueryHandler {
    private final AccountRepository accountRepository;

    public AccountQueryHandler(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @QueryHandler // Écoute la requête GetAllAccountsQuery [22]
    public List<Account> on(GetAllAccountsQuery query) {
        return accountRepository.findAll();
    }
}
