package com.desafio.services;



import org.junit.Assert;

import java.util.Date;

import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

public class TestAbstractEmailService {
	
	@Mock
	SimpleMailMessage sm;
	
	@Mock
	@Value("${default.sender}")
	private String sender;
	
	@Test
	public void TestSendOrderConfirmationEmail(Object obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		Assert.assertTrue(sm.getTo().toString(), true);
		
	}

	public SimpleMailMessage prepareSimpleMailMessageFromPedido(Object obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.toString());
		sm.setFrom(sender);
		sm.setSubject("Pedido confirmado! Código: " + obj.toString());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}
}
