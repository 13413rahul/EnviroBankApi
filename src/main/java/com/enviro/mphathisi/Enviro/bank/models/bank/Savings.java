package com.enviro.mphathisi.Enviro.bank.models.bank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Savings {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int accountNumber;
    private BigDecimal accountBalance;

    @OneToMany(mappedBy = "savings", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<SavingsTransaction> savingsTransactionList = new java.util.ArrayList<>();
}
