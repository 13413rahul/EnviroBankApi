package com.enviro.mphathisi.Enviro.bank.services;
import com.enviro.mphathisi.Enviro.bank.models.BankAccount;
import com.enviro.mphathisi.Enviro.bank.models.constants.AccountType;


import java.math.BigDecimal;
import java.util.List;

public interface BankAcountService {
    public BankAccount create(BankAccount bankAccount);

    public List<BankAccount> bankAccounts();

    public BankAccount getAccountById(Long id);

    void deposit(String senderAccountNo ,String receiverAccountNO, BigDecimal amount);
    void transferTo(String sederAccountNo, String receiverAccountNo, BigDecimal amount, String from , String to);

}
