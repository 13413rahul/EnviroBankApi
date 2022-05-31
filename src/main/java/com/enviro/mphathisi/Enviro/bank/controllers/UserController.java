package com.enviro.mphathisi.Enviro.bank.controllers;

import com.enviro.mphathisi.Enviro.bank.models.User;
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
public class UserController {
    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }
    public IUserService iUserService;

    @Autowired
    private EmailNotification emailNotification;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        List<User> userList = iUserService.users();
        return new ResponseEntity<>(userList , HttpStatus.OK);
    }

    @GetMapping({"/users/{userId}"})
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        return new ResponseEntity<>(iUserService.getUserById(userId), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> saveNewUser(@RequestBody User user) {

        User user1 = iUserService.create(user);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("/", "/api/v1" + user1.getUser_id());

        // registration success massage to user email

        String emailBody = "";
        emailBody = emailBody +
                "userName: " + user1.getUsername() + "\n" +
                "message: " + user1.getUsername() + "has successfully registered !!!";

        try
        {
            emailNotification.sendEmailNotification(user1.getEmail(), emailBody, "successfully user register");
        }
        catch(MailException ex) {
        }
        return new ResponseEntity<>(user1, httpHeaders, HttpStatus.CREATED);
    }


}
