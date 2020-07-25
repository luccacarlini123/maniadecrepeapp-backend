package com.mouzetech.maniadecrepeapp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mouzetech.maniadecrepeapp.domain.Pedido;
import com.mouzetech.maniadecrepeapp.repositories.PedidoRepository;
import com.mouzetech.maniadecrepeapp.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido buscarPorId(Integer id) {
		Optional<Pedido> obj = pedidoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+id+". Tipo: "+Pedido.class));
	}
}