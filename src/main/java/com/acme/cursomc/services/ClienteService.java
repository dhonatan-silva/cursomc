package com.acme.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.cursomc.domain.Cliente;
import com.acme.cursomc.repositories.ClienteRepository;
import com.acme.cursomc.services.exception.ObjectNofFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		Cliente obj = repo.getOne(id);
		if(obj == null) {
			throw new ObjectNofFoundException("Objeto não encontrado! Id: " + id
					+ ", Tipo: " + Cliente.class.getSimpleName());
		}
		return obj;
	}

}
