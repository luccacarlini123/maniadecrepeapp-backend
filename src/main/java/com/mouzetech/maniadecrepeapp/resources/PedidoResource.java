package com.mouzetech.maniadecrepeapp.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mouzetech.maniadecrepeapp.domain.Pedido;
import com.mouzetech.maniadecrepeapp.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Pedido> findById(@PathVariable Integer id){
		Pedido obj = pedidoService.buscarPorId(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj) {
		obj = pedidoService.inserir(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
