package com.enviro.mphathisi.Enviro.bank.controllers.request;

import java.math.BigDecimal;


import com.enviro.mphathisi.Enviro.bank.models.constants.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AdminPaymentRequest {
    private String enviroBankAccountNo;
    private String receiverAccountNo;
    private AccountType accountType;
    private BigDecimal amount;
}
