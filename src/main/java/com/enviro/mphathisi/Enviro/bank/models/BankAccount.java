package com.enviro.mphathisi.Enviro.bank.models;

import com.enviro.mphathisi.Enviro.bank.models.bank.Savings;
import com.enviro.mphathisi.Enviro.bank.models.constants.AccountStatus;
import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Table(name = "BankAccount")
public class BankAccount {
    @Id
    @GeneratedValue
    @Column(name ="bank_account_id", updatable = false, nullable = false)
    Long bank_account_id;
    String accountNumber;
    @Column
    private String accountType;
    @Column
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
    @Column
    BigDecimal availableBalance;
    @Column
    BigDecimal latestBalance;

    @OneToMany(mappedBy = "bankAccount")
    private List<Transaction> transactionList;


}
