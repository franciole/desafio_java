package com.desafio.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.desafio.entities.Usuario;
import com.desafio.repositories.UsuarioRepository;
import com.desafio.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	private Random rand = new Random();
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private BCryptPasswordEncoder pe;

	public void sendNewPassword(String email) {
		Usuario usuario = usuarioRepository.findByEmail(email);
		if (usuario == null) {
			throw new ObjectNotFoundException("Email não encotrado");
		}

		String newPass = newPassword();
		usuario.setSenha(pe.encode(newPass));

		usuarioRepository.save(usuario);
		emailService.sendNewPasswordEmail(usuario, newPass);
	}

	private String newPassword() {
		char vet[] = new char[10];

		for (int i = 0; i < vet.length; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) { // gera um dígito
			return (char) (rand.nextInt(10) + 48);
		}
		if (opt == 1) { // gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		} else {
			return (char) (rand.nextInt(26) + 97);
		}
	}
}
