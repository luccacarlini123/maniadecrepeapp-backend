package com.mouzetech.maniadecrepeapp.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mouzetech.maniadecrepeapp.domain.Cliente;
import com.mouzetech.maniadecrepeapp.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Integer id){
		Cliente obj = clienteService.buscarPorId(id);
		return ResponseEntity.ok().body(obj);
	}
	
}
