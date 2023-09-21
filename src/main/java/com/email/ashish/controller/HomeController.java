package com.email.ashish.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.email.ashish.model.EmailRequest;
import com.email.ashish.service.EmailService;

@RestController
public class HomeController {
	@Autowired
	private EmailService emailService;
	
	@GetMapping("/welcome")
	public String welcomeHandler() {
		
		return "Hello This welcome messege from EmailApi";
	}

	
	//Api to send email
	@PostMapping("/sendemail")
	public ResponseEntity<?>sendEmail(@RequestBody EmailRequest request){
		
		boolean result=this.emailService.sendEmail(request.getSubject(),request.getMessage(),request.getTo(),request.getFrom());
		System.out.println(request);
		
		if(result) {
			return ResponseEntity.ok("Email Sent Successfully.....");
		}
		else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro in Sending Email");
		}
		
	}
}
