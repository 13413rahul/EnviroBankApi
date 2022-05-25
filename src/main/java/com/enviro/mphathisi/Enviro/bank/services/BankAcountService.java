package com.enviro.mphathisi.Enviro.bank.services;
import com.enviro.mphathisi.Enviro.bank.controllers.request.TransferBalanceRequest;
import com.enviro.mphathisi.Enviro.bank.models.BankAccount;
import com.enviro.mphathisi.Enviro.bank.models.Transaction;
import com.enviro.mphathisi.Enviro.bank.models.bank.Cheque;
import com.enviro.mphathisi.Enviro.bank.models.bank.Credit;
import com.enviro.mphathisi.Enviro.bank.models.bank.Recipient;
import com.enviro.mphathisi.Enviro.bank.models.bank.Savings;


import java.math.BigDecimal;
import java.util.List;

public interface BankAcountService {
    public BankAccount create(BankAccount bankAccount);

    public List<BankAccount> bankAccounts();

    public BankAccount getAccountById(Long id);

    void toSomeoneElseTransfer(TransferBalanceRequest transferBalanceRequest);

    void depositTome(TransferBalanceRequest transferBalanceRequest);
}
