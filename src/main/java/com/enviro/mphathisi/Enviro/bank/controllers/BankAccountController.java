package com.enviro.mphathisi.Enviro.bank.controllers;



import com.enviro.mphathisi.Enviro.bank.controllers.request.TransferBalanceRequest;
import com.enviro.mphathisi.Enviro.bank.models.BankAccount;
import com.enviro.mphathisi.Enviro.bank.models.Transaction;
import com.enviro.mphathisi.Enviro.bank.models.User;
import com.enviro.mphathisi.Enviro.bank.services.BankAcountService;
import com.enviro.mphathisi.Enviro.bank.services.IUserService;
import com.enviro.mphathisi.Enviro.bank.services.notification.EmailNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1")
@RestController
public class BankAccountController {

    @Autowired
    public BankAcountService bankAcountService;

    @Autowired
    private EmailNotification emailNotification;

    @Autowired
    public IUserService iUserService;

    @GetMapping("/beneficiaries")
    public ResponseEntity<List<BankAccount>> getAccounts() {
        List<BankAccount> accountList = bankAcountService.bankAccounts();
        return new ResponseEntity<>(accountList, HttpStatus.OK);
    }

    @GetMapping({"/beneficiaries/{bankAccountId}"})
    public ResponseEntity<BankAccount> getAccount(@PathVariable Long bankAccountId) {
        return new ResponseEntity<>(bankAcountService.getAccountById(bankAccountId), HttpStatus.OK);
    }

    @PostMapping("/newBankAccount")
    public ResponseEntity<BankAccount> saveNewAccount(@RequestBody BankAccount bankAccount) {
        BankAccount bankAccount1 = bankAcountService.create(bankAccount);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("/", "/api/v1" + bankAccount1.getBank_account_id().toString());

        // create new Account

        // fetch use information
        List<User> userList = iUserService.users();

        String emailBody = "";
        emailBody = emailBody +
                "message: " + bankAccount1.getAccountNumber() + "has successfully opened !!!";

        findEmailAndSendNotification(userList, emailBody, bankAccount1, "successfully open new Account");

        return new ResponseEntity<>(bankAccount1, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/depositToDifferentAccounts")
    public ResponseEntity<Transaction> depositMoney(@RequestBody BankAccount from, BankAccount to, TransferBalanceRequest transferBalanceRequest) {
        bankAcountService.sendToDifferentAccount(from, to, transferBalanceRequest);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("/", "/api/v1");

        //implementing email service
        // fetch use information
        List<User> userList = iUserService.users();

        // debited
        String debitedBody = "";
        debitedBody = debitedBody +
                transferBalanceRequest.getAmount() + "is debited from " +
                from.getAccountNumber();

        findEmailAndSendNotification(userList, debitedBody, from, "debited Amount");


        //created
        String createdBody = "";
        createdBody = createdBody +
                transferBalanceRequest.getAmount() + "is created to " +
                to.getAccountNumber();

        findEmailAndSendNotification(userList, createdBody, to, "created");

        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    void findEmailAndSendNotification(List<User> userList, String emailBody, BankAccount bankAccount, String subject) {
        String email = "";
        for(User user : userList) {
            if(user.getBankAccountList().contains(bankAccount)) {
                email = user.getEmail();
                break;
            }
        }
        try
        {
            emailNotification.sendEmailNotification(email, emailBody, subject);
        }
        catch(MailException ex) {

        }
    }

}