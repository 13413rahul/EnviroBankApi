package com.enviro.mphathisi.Enviro.bank.repository;

import com.enviro.mphathisi.Enviro.bank.models.bank.Savings;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingsRepository extends CrudRepository<Savings ,Long> {
}
