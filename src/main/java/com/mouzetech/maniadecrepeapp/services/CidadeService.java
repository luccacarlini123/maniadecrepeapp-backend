package com.mouzetech.maniadecrepeapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mouzetech.maniadecrepeapp.domain.Cidade;
import com.mouzetech.maniadecrepeapp.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repo;
	
	public List<Cidade> buscarCidadePorEstadoId(Integer id){
		return repo.findCidadeByEstadoId(id);
	}
}