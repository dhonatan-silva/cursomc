package com.acme.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.acme.cursomc.domain.Categoria;
import com.acme.cursomc.repositories.CategoriaRepository;
import com.acme.cursomc.services.exception.DataIntegrityException;
import com.acme.cursomc.services.exception.ObjectNofFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
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

	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos");
		}
		
	}
	
	public List<Categoria> findAll(){
		return repo.findAll();
	}
}
