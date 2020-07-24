package com.mouzetech.maniadecrepeapp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mouzetech.maniadecrepeapp.domain.Cliente;
import com.mouzetech.maniadecrepeapp.repositories.ClienteRepository;
import com.mouzetech.maniadecrepeapp.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente buscarPorId(Integer id) {
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> 
		new ObjectNotFoundException("Objeto não encontrado! Id: "+id+". Tipo: "+Cliente.class));
	}
	
}
