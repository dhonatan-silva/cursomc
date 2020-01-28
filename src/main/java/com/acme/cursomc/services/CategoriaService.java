package com.acme.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.cursomc.domain.Categoria;
import com.acme.cursomc.repositories.CategoriaRepository;
import com.acme.cursomc.services.exception.ObjectNofFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Categoria obj = repo.getOne(id);
		if(obj == null) {
			throw new ObjectNofFoundException("Objeto não encontrado! Id: " + id
					+ ", Tipo: " + Categoria.class.getSimpleName());
		}
		return obj;
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

}
