package com.enviro.mphathisi.Enviro.bank.repository;

import com.enviro.mphathisi.Enviro.bank.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    public User getUserByUsername(String username);
}
