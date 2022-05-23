package com.enviro.mphathisi.Enviro.bank.services;
import com.enviro.mphathisi.Enviro.bank.controllers.request.TransferBalanceRequest;
import com.enviro.mphathisi.Enviro.bank.models.BankAccount;
import com.enviro.mphathisi.Enviro.bank.models.Transaction;


import java.util.List;

public interface BankAcountService {
    public BankAccount create(BankAccount bankAccount);

    public List<BankAccount> bankAccounts();

    public BankAccount getAccountById(Long id);

    public void deposit(Long id,  BankAccount bankAccount);

    void sendToDifferentAccount(BankAccount from , BankAccount to ,TransferBalanceRequest  transferBalanceRequest);


}
