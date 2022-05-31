package com.enviro.mphathisi.Enviro.bank.services;

import com.enviro.mphathisi.Enviro.bank.models.User;

import java.util.List;

public interface IUserService {
    public User create(User user);
    public List<User> users();
    public User getUserById(Long id);
    public User findByUserName(String username);

   //a method to link users


}
