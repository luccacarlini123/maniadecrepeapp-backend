package com.mouzetech.maniadecrepeapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mouzetech.maniadecrepeapp.domain.Cliente;
import com.mouzetech.maniadecrepeapp.dto.ClienteDTO;
import com.mouzetech.maniadecrepeapp.repositories.ClienteRepository;
import com.mouzetech.maniadecrepeapp.services.exception.DataIntegrityException;
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
	
	public Cliente editar(Cliente obj) {
		Cliente newObj = buscarPorId(obj.getId());
		updateData(newObj, obj);
		return clienteRepository.save(newObj);
	}
	
	public List<Cliente> buscarTodos(){
		return clienteRepository.findAll();
	}
	
	public Page<Cliente> buscarPagina(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageReq = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageReq);
	}
	
	public void excluir(Integer id) {
		buscarPorId(id);
		try {
			clienteRepository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir, há entidades associadas.");
		}
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
}
