package com.simon.world_banking_app_v1.service;

import com.simon.world_banking_app_v1.domain.dto.EmailDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService{

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username")
    private String senderEmail;

    @Override
    public void sendEmailAlert(EmailDetails emailDetails){

        try{
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            simpleMailMessage.setFrom(senderEmail);
            simpleMailMessage.setTo(emailDetails.getRecipient());
            simpleMailMessage.setText(emailDetails.getMessageBody());
            simpleMailMessage.setSubject(emailDetails.getSubject());

            javaMailSender.send(simpleMailMessage);

            System.out.println("Mail sent successfully");

        }catch(MailException e){
            throw new RuntimeException("Email not sent");
        }
    }
}
