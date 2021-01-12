package com.desafio.services;

import org.springframework.mail.SimpleMailMessage;

import com.desafio.entities.Pedido;
import com.desafio.entities.Usuario;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);

	void sendEmail(SimpleMailMessage msg);
	
	void sendNewPasswordEmail(Usuario usuario, String newPass);
}
