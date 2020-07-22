package com.mouzetech.maniadecrepeapp.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mouzetech.maniadecrepeapp.domain.Categoria;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

	@GetMapping
	public List<Categoria> listar() {
		Categoria cat1 = new Categoria(1, "Informática");
		Categoria cat2 = new Categoria(1, "Jardinagem");
		
		List<Categoria> list = new ArrayList<>();
		list.add(cat1);
		list.add(cat2);
		return list;
	}
	
}
