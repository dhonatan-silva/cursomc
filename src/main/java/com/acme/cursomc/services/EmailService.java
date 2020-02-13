package com.acme.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.acme.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

}
