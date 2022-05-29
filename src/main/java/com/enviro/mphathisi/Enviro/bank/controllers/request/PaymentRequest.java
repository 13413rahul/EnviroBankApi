package com.enviro.mphathisi.Enviro.bank.controllers.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PaymentRequest {
    //Sending to same user
    private String from;
    private String to;
    private String senderAccountNo;
    private String receiverAccountNo;
    private BigDecimal amount;
}
