package com.enviro.mphathisi.Enviro.bank.services;

import com.enviro.mphathisi.Enviro.bank.models.BankAccount;

import com.enviro.mphathisi.Enviro.bank.models.constants.AccountStatus;
import com.enviro.mphathisi.Enviro.bank.models.constants.AccountType;
import com.enviro.mphathisi.Enviro.bank.repository.BankAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;


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
    public void deposit(String senderAccountNo, String receiverAccountNo, BigDecimal amount) {
        //sending  between accounts
        BankAccount sender = bankAccountRepository.findByAccountNumber(senderAccountNo);
        BankAccount receiver = bankAccountRepository.findByAccountNumber(receiverAccountNo);
        System.out.println("Sender available balance:"+sender.getAvailableBalance());
        System.out.println("Receiver balance:"+receiver.getAvailableBalance());

        Date date = new  Date();
        Long time =  date.getTime();
        System.out.println(time);

        //implement time delay

        BigDecimal senderAmount = sender.getAvailableBalance().subtract(amount);
        sender.setAvailableBalance(senderAmount);
        System.out.println("Sender left with:" + sender.getAvailableBalance());
        BigDecimal receiverAmount = receiver.getAvailableBalance().add(amount);
        receiver.setAvailableBalance(receiverAmount);
        receiver.setLatestBalance(receiverAmount);
        System.out.println("Receiver have " + receiver.getAvailableBalance());
        bankAccountRepository.save(sender);
        bankAccountRepository.save(receiver);
    }

    @Override
    public void transferTo(String sederAccountNo, String receiverAccountNo, BigDecimal amount, String from ,String to) {
        if(from.equals(AccountType.SAVINGS.toString()) && to.equals(AccountType.CREDIT.toString())){
            BankAccount sender = bankAccountRepository.findByAccountNumber(sederAccountNo);
            BankAccount receiver = bankAccountRepository.findByAccountNumber(receiverAccountNo);
            System.out.println("Sender available balance:"+sender.getAvailableBalance());
            System.out.println("Receiver balance:"+receiver.getAvailableBalance());

            BigDecimal senderAmount = sender.getAvailableBalance().subtract(amount);
            sender.setAvailableBalance(senderAmount);
            System.out.println("Sender left with:" + sender.getAvailableBalance());
            BigDecimal receiverAmount = receiver.getAvailableBalance().add(amount);

             Date date = new  Date();
             Long time =  date.getTime();
             System.out.println(time);
            receiver.setAvailableBalance(receiverAmount);
            receiver.setLatestBalance(receiverAmount);
            System.out.println("Receiver have " + receiver.getAvailableBalance());
            bankAccountRepository.save(sender);
            bankAccountRepository.save(receiver);
        }
        else {
            System.out.println("wrong selection");
        }

    }



}
