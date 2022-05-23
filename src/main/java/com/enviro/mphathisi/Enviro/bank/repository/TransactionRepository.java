package com.enviro.mphathisi.Enviro.bank.repository;

import com.enviro.mphathisi.Enviro.bank.models.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction , Long>{
}
