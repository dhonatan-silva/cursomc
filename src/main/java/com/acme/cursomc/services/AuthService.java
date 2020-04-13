package com.acme.cursomc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.acme.cursomc.domain.Cliente;
import com.acme.cursomc.repositories.ClienteRepository;
import com.acme.cursomc.services.exception.ObjectNofFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private EmailService emailService;

	private Random rand = new Random();

	public void sendNewPassWord(String email) {
		Cliente cliente = clienteRepository.findByEmail(email);

		if (cliente == null) {
			throw new ObjectNofFoundException("Email não encontrado");
		}

		String newPass = newPassword();
		cliente.setSenha(pe.encode(newPass));

		clienteRepository.save(cliente);
		emailService.sendNewPassworEmail(cliente, newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];

		for (int i = 0; i < 10; i++) {
			vet[i] = randoChar();
		}
		return new String(vet);
	}

	private char randoChar() {
		int opt = rand.nextInt(3);
		if (opt == 0) { // gera um digito
			return (char) (rand.nextInt(10) + 48);
		} else if (opt == 1) { // gera uma letra maiuscula
			return (char) (rand.nextInt(26) + 65);
		} else {// gera letra minuscula
			return (char) (rand.nextInt(26) + 97);
		}
	}

}
