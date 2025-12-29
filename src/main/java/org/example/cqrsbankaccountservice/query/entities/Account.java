package org.example.cqrsbankaccountservice.query.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.cqrsbankaccountservice.common.enums.AccountStatus;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {
    @Id
    private String id;
    private double balance;
    private AccountStatus status;
    private String currency;
}
