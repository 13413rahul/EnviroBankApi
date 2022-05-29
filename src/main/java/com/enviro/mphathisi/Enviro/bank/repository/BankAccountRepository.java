package com.enviro.mphathisi.Enviro.bank.repository;

import com.enviro.mphathisi.Enviro.bank.models.BankAccount;
import com.enviro.mphathisi.Enviro.bank.models.constants.AccountType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends CrudRepository<BankAccount,Long> {
    BankAccount findByAccountNumber(String accountNumber);
    BankAccount findByAccountType(AccountType accountType);
}
