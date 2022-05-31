package com.enviro.mphathisi.Enviro.bank.controllers;



import com.enviro.mphathisi.Enviro.bank.controllers.request.AdminPaymentRequest;
import com.enviro.mphathisi.Enviro.bank.controllers.request.PaymentRequest;
import com.enviro.mphathisi.Enviro.bank.controllers.request.TransferRequest;
import com.enviro.mphathisi.Enviro.bank.models.BankAccount;
import com.enviro.mphathisi.Enviro.bank.models.constants.AccountType;
import com.enviro.mphathisi.Enviro.bank.services.BankAcountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1")
@RestController
public class BankAccountController {

    @Autowired
    public BankAcountService bankAcountService;

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
        return new ResponseEntity<>(bankAccount1, httpHeaders, HttpStatus.CREATED);
    }

    @PostMapping("/deposit")
    public void sendSomeFunds(@RequestBody TransferRequest transferRequest){
        bankAcountService.deposit(transferRequest.getSenderAccountNo() , transferRequest.getReceiverAccountNo(),
                transferRequest.getAmount());
    }
    //transfer to same user account
    @PostMapping("/transfer")
    public  void transfer(@RequestBody PaymentRequest paymentRequest){
        bankAcountService.transferTo(paymentRequest.getSenderAccountNo(), paymentRequest.getReceiverAccountNo(),
                    paymentRequest.getAmount() , paymentRequest.getFrom(), paymentRequest.getTo());
    }





}