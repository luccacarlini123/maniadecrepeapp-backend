package com.mouzetech.maniadecrepeapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mouzetech.maniadecrepeapp.domain.Categoria;
import com.mouzetech.maniadecrepeapp.domain.Produto;
import com.mouzetech.maniadecrepeapp.repositories.CategoriaRepository;
import com.mouzetech.maniadecrepeapp.repositories.ProdutoRepository;
import com.mouzetech.maniadecrepeapp.services.exception.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private CategoriaRepository categoriaRepo;

	@Autowired
	private ProdutoRepository produtoRepo;
	
	public Produto buscarPorId(Integer id) {
		Optional<Produto> obj = produtoRepo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado! Id: "+id+", Tipo: "+Produto.class));
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepo.findAllById(ids);
		return produtoRepo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}
	
	public List<Produto> buscarPorCategoria(String nome){
		return produtoRepo.buscarPorCategoria(nome);
	}
	
	public List<Produto> buscarTodos(){
		List<Produto> list = produtoRepo.findAll();
		return list;
	}
	
}
