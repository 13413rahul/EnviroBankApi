package com.enviro.mphathisi.Enviro.bank.controllers;



import com.enviro.mphathisi.Enviro.bank.controllers.request.TransferBalanceRequest;
import com.enviro.mphathisi.Enviro.bank.models.BankAccount;
import com.enviro.mphathisi.Enviro.bank.models.Transaction;
import com.enviro.mphathisi.Enviro.bank.models.User;
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

    @PutMapping("/depositToDifferentAccounts")
    public ResponseEntity<Transaction> depositMoney(@RequestBody BankAccount from, BankAccount to, TransferBalanceRequest transferBalanceRequest) {
        bankAcountService.sendToDifferentAccount(from, to, transferBalanceRequest);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("/", "/api/v1");
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

}