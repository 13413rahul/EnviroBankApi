package com.enviro.mphathisi.Enviro.bank.services;

import com.enviro.mphathisi.Enviro.bank.controllers.request.TransferBalanceRequest;
import com.enviro.mphathisi.Enviro.bank.models.BankAccount;
import com.enviro.mphathisi.Enviro.bank.models.Transaction;
import com.enviro.mphathisi.Enviro.bank.models.constants.AccountStatus;
import com.enviro.mphathisi.Enviro.bank.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAcountService{
    @Autowired
    BankAccountRepository bankAccountRepository;

    @Override
    public BankAccount create(BankAccount bankAccount) {

        //remove accountStatus and  latestBalance on the form fields
        BigDecimal zero = new BigDecimal(0);
        if(bankAccount.getAvailableBalance().equals(zero)){
            bankAccount.setAccountStatus(AccountStatus.PENDING);
        }
        else{
            bankAccount.setAccountStatus(AccountStatus.ACTIVE);
        }

        //this is not working yet

        bankAccount.setLatestBalance(bankAccount.getAvailableBalance());
        return bankAccountRepository.save(bankAccount);
    }

    @Override
    public List<BankAccount> bankAccounts() {
        List<BankAccount> bankAccountList = new ArrayList<>();
        bankAccountRepository.findAll().forEach(bankAccountList::add);
        return bankAccountList;
    }

    @Override
    public BankAccount getAccountById(Long id) {
        return bankAccountRepository.findById(id).get();
    }

    @Override
    public void deposit(Long id,  BankAccount bankAccount) {
        BankAccount bankAccount1 = bankAccountRepository.findById(id).get();
        bankAccountRepository.findByAccountNumber(bankAccount1.getAccountNumber());
        bankAccountRepository.findByAccountType(bankAccount1.getAccountType());
        bankAccount1.setAvailableBalance(bankAccount.getAvailableBalance());
        bankAccountRepository.save(bankAccount1);

    }

    @Override
    public void sendToDifferentAccount(BankAccount from, BankAccount to, TransferBalanceRequest transferBalanceRequest) {
        from.setAccountNumber(transferBalanceRequest.getFromAccountNumber());
        to.setAccountNumber(transferBalanceRequest.getToAccountNumber());
       // BigDecimal take = from.getAvailableBalance().subtract(transferBalanceRequest.getAmount());
       // BigDecimal  add = to.getAvailableBalance().add(transferBalanceRequest.getAmount());
        //from.setAvailableBalance(take);
       // to.setAvailableBalance(add);
        System.out.println(from.getAvailableBalance());
        System.out.println(to.getAvailableBalance());
        //let me try to send to different accounts
        //and then same account
        // implement email send
        // host
       // bankAccountRepository.save(from);
    }

}
