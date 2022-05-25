package com.enviro.mphathisi.Enviro.bank.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferBalanceRequest {
    private String fromAccountNumber;
    private String toAccountNumber;
    private String toAccountType;
    private String fromAccountType;
    private BigDecimal amount;

}
