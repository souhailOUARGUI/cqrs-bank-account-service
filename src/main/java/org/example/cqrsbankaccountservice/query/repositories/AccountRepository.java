package org.example.cqrsbankaccountservice.query.repositories;

import org.example.cqrsbankaccountservice.query.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {

}
