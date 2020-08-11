package com.mouzetech.maniadecrepeapp.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mouzetech.maniadecrepeapp.domain.ItemPedido;
import com.mouzetech.maniadecrepeapp.domain.PagamentoComBoleto;
import com.mouzetech.maniadecrepeapp.domain.Pedido;
import com.mouzetech.maniadecrepeapp.domain.enums.EstadoPagamento;
import com.mouzetech.maniadecrepeapp.repositories.ItemPedidoRepository;
import com.mouzetech.maniadecrepeapp.repositories.PagamentoRepository;
import com.mouzetech.maniadecrepeapp.repositories.PedidoRepository;
import com.mouzetech.maniadecrepeapp.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private BoletoService boletoService;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private ProdutoService produtoService;
	
	public Pedido buscarPorId(Integer id) {
		Optional<Pedido> obj = pedidoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+id+". Tipo: "+Pedido.class));
	}
	
	public Pedido inserir(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = pedidoRepository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for(ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.buscarPorId(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
	}
}