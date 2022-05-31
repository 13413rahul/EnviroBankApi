package com.enviro.mphathisi.Enviro.bank.services.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailNotification {

    @Autowired
    public JavaMailSender emailSender;

    public void sendEmailNotification(String userEmail, String body, String subject) throws MailException {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(userEmail);
        message.setSubject(subject);
        message.setText(body);

        emailSender.send(message);

    }

}
