package com.bsokolovskyi.bridge.web.service;

import com.bsokolovskyi.bridge.web.db.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Value("${spring.mail.username}")
    private String fromMail;

    private JavaMailSender javaMailSender;

    public NotificationService(@Autowired JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendLoginNotification(User user) throws MailException, InterruptedException {
        // for example
        Thread.sleep(1000);

        SimpleMailMessage mail = createMail();
        mail.setTo(user.getEmail());
        mail.setSubject("Login into Bridge game website");
        mail.setText("You success logged in Bridge game account");

        javaMailSender.send(mail);
    }

    public void sendRegisterNotification(User user) throws MailException, InterruptedException {
        // for example
        Thread.sleep(1000);

        SimpleMailMessage mail = createMail();
        mail.setTo(user.getEmail());
        mail.setSubject("Register in Bridge game website");
        mail.setText("You success registered in Bridge game website");

        javaMailSender.send(mail);
    }

    private SimpleMailMessage createMail() {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(fromMail);

        return mail;
    }
}
