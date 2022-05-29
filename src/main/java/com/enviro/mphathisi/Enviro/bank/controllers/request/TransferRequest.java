package com.enviro.mphathisi.Enviro.bank.controllers.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TransferRequest {
    private String senderAccountNo;
    private String receiverAccountNo;
    private BigDecimal amount;
}
