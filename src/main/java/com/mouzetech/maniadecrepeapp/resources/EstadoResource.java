package com.mouzetech.maniadecrepeapp.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mouzetech.maniadecrepeapp.domain.Cidade;
import com.mouzetech.maniadecrepeapp.domain.Estado;
import com.mouzetech.maniadecrepeapp.dto.CidadeDTO;
import com.mouzetech.maniadecrepeapp.dto.EstadoDTO;
import com.mouzetech.maniadecrepeapp.services.CidadeService;
import com.mouzetech.maniadecrepeapp.services.EstadoService;

@RestController
@RequestMapping(value="/estados")
public class EstadoResource {

	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private CidadeService cidadeService;
	
	@GetMapping
	public ResponseEntity<List<EstadoDTO>> findAll(){
		List<Estado> list = estadoService.buscarTodos();
		List<EstadoDTO> listDTO = list.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@GetMapping(value="/{estadoId}/cidades")
	public ResponseEntity<List<CidadeDTO>> findCidadesByEstadoId(@PathVariable("estadoId") Integer estado_id){
		List<Cidade> list = cidadeService.buscarCidadePorEstadoId(estado_id);
		List<CidadeDTO> listDTO = list.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
}