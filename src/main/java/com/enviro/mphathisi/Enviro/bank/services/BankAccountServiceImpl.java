package com.enviro.mphathisi.Enviro.bank.services;

import com.enviro.mphathisi.Enviro.bank.controllers.request.TransferBalanceRequest;
import com.enviro.mphathisi.Enviro.bank.models.BankAccount;
import com.enviro.mphathisi.Enviro.bank.models.bank.*;
import com.enviro.mphathisi.Enviro.bank.models.bank.repo.CreditRepository;
import com.enviro.mphathisi.Enviro.bank.models.constants.AccountStatus;
import com.enviro.mphathisi.Enviro.bank.repository.BankAccountRepository;
import com.enviro.mphathisi.Enviro.bank.repository.ChequeRepository;
import com.enviro.mphathisi.Enviro.bank.repository.SavingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class BankAccountServiceImpl implements BankAcountService{
    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    SavingsRepository savingsRepository;

    @Autowired
    ChequeRepository chequeRepository;

    @Autowired
    CreditRepository creditRepository;


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
    public void toSomeoneElseTransfer(TransferBalanceRequest transferBalanceRequest) {

    }

    @Override
    public void depositTome(TransferBalanceRequest transferBalanceRequest) {
        BankAccount bankAccount = new BankAccount();

        String toAccountType = transferBalanceRequest.getToAccountType();
        String fromAccountType = transferBalanceRequest.getFromAccountType();

            Savings savings = new Savings();
            Credit credit = new Credit();

            BigDecimal initialDeposit = new BigDecimal(200);
            savings.setAccountBalance(initialDeposit);
            savingsRepository.save(savings);
            credit.setAccountBalance(initialDeposit);
            creditRepository.save(credit);

        if(Objects.equals(toAccountType, "Credit") && Objects.equals(fromAccountType ,"Savings")){
          BigDecimal subtract =   savings.getAccountBalance().subtract(transferBalanceRequest.getAmount());
          savings.setAccountBalance(subtract);
          savingsRepository.save(savings);
          BigDecimal add = credit.getAccountBalance().add(transferBalanceRequest.getAmount());
          credit.setAccountBalance(add);
          creditRepository.save(credit);

           bankAccount.setAccountType("Credit");
           bankAccount.setAvailableBalance(credit.getAccountBalance());
           System.out.println("bank account"+bankAccount.getAvailableBalance());

            System.out.println("take from savings and add to credit account");
            System.out.println("Saving left with :"+savings.getAccountBalance());
            System.out.println("Credit has new Balance of : "+credit.getAccountBalance());

        }


    }

}
