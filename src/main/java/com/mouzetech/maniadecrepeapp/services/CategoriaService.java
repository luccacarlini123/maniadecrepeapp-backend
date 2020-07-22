package com.mouzetech.maniadecrepeapp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mouzetech.maniadecrepeapp.domain.Categoria;
import com.mouzetech.maniadecrepeapp.repositories.CategoriaRepository;
import com.mouzetech.maniadecrepeapp.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscarPorId(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Id "+id+" não encontrado. Tipo: "+Categoria.class+""));
	}
	
}
