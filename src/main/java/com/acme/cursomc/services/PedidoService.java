package com.acme.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.cursomc.domain.Pedido;
import com.acme.cursomc.repositories.PedidoRepository;
import com.acme.cursomc.services.exception.ObjectNofFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Pedido obj = repo.getOne(id);
		if(obj == null) {
			throw new ObjectNofFoundException("Objeto não encontrado! Id: " + id
					+ ", Tipo: " + Pedido.class.getSimpleName());
		}
		return obj;
	}

}
