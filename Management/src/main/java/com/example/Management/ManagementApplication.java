package com.example.Management;

import com.example.Management.Service.EmailSenderService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableFeignClients
public class ManagementApplication {
//	@Autowired
//	private EmailSenderService senderService;

	public static void main(String[] args) {
		SpringApplication.run(ManagementApplication.class, args);
	}

//	@EventListener(ApplicationReadyEvent.class)
//	public void triggerMail() throws MessagingException {
//	 	senderService.sendSimpleEmail("bharathhareesh2002@gmail.com",
//				"This is email body",
//				"This is email subject");
//
//	}
}
