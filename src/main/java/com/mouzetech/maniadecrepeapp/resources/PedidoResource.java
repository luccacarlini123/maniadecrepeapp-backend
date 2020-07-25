package com.mouzetech.maniadecrepeapp.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
}
