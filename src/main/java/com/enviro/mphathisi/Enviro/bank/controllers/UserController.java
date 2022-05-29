package com.enviro.mphathisi.Enviro.bank.controllers;

import com.enviro.mphathisi.Enviro.bank.controllers.request.PaymentRequest;
import com.enviro.mphathisi.Enviro.bank.models.User;
import com.enviro.mphathisi.Enviro.bank.services.IUserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1")
@RestController
public class UserController {
    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }
    public IUserService iUserService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        List<User> userList = iUserService.users();
        return new ResponseEntity<>(userList , HttpStatus.OK);
    }

    @GetMapping({"/users/{userId}"})
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        return new ResponseEntity<>(iUserService.getUserById(userId), HttpStatus.OK);
    }

    @GetMapping({"/{username}"})
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        return new ResponseEntity<>(iUserService.findByUserName(username), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> saveNewUser(@RequestBody User user) {
        User user1 = iUserService.create(user);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("/", "/api/v1" + user1.getUser_id());
        return new ResponseEntity<>(user1, httpHeaders, HttpStatus.CREATED);
    }
    




}
