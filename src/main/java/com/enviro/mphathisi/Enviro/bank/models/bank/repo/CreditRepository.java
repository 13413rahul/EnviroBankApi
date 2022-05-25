package com.enviro.mphathisi.Enviro.bank.models.bank.repo;

import com.enviro.mphathisi.Enviro.bank.models.bank.Credit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRepository extends CrudRepository<Credit ,Long> {
}
