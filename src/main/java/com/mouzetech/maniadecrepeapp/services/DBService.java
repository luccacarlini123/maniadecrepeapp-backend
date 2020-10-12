package com.mouzetech.maniadecrepeapp.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mouzetech.maniadecrepeapp.domain.Categoria;
import com.mouzetech.maniadecrepeapp.domain.Cidade;
import com.mouzetech.maniadecrepeapp.domain.Cliente;
import com.mouzetech.maniadecrepeapp.domain.Endereco;
import com.mouzetech.maniadecrepeapp.domain.Estado;
import com.mouzetech.maniadecrepeapp.domain.ItemPedido;
import com.mouzetech.maniadecrepeapp.domain.Pagamento;
import com.mouzetech.maniadecrepeapp.domain.PagamentoComBoleto;
import com.mouzetech.maniadecrepeapp.domain.PagamentoComCartao;
import com.mouzetech.maniadecrepeapp.domain.Pedido;
import com.mouzetech.maniadecrepeapp.domain.Produto;
import com.mouzetech.maniadecrepeapp.domain.enums.EstadoPagamento;
import com.mouzetech.maniadecrepeapp.domain.enums.Perfil;
import com.mouzetech.maniadecrepeapp.domain.enums.TipoCliente;
import com.mouzetech.maniadecrepeapp.repositories.CategoriaRepository;
import com.mouzetech.maniadecrepeapp.repositories.CidadeRepository;
import com.mouzetech.maniadecrepeapp.repositories.ClienteRepository;
import com.mouzetech.maniadecrepeapp.repositories.EnderecoRepository;
import com.mouzetech.maniadecrepeapp.repositories.EstadoRepository;
import com.mouzetech.maniadecrepeapp.repositories.ItemPedidoRepository;
import com.mouzetech.maniadecrepeapp.repositories.PagamentoRepository;
import com.mouzetech.maniadecrepeapp.repositories.PedidoRepository;
import com.mouzetech.maniadecrepeapp.repositories.ProdutoRepository;

@Service
public class DBService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired
	private BCryptPasswordEncoder pe;
	

	public void instantiateTestDataBase() throws ParseException {
		Categoria cat1 = new Categoria(null, "Crepe Salgado");
		Categoria cat2 = new Categoria(null, "Crepe Doce");
		
		Produto p1 = new Produto(null, "Queijo e presunto", 10.00);
		Produto p2 = new Produto(null, "4 Queijos", 12.00);
		Produto p3 = new Produto(null, "Frango com queijo", 14.00);
		Produto p4 = new Produto(null, "Frango com catupiry", 15.00);
		Produto p5 = new Produto(null, "Queijo, calabresa e bacon", 16.00);
		Produto p6 = new Produto(null, "Frango, queijo e bacon", 17.00);
		Produto p7 = new Produto(null, "Carne seca com queijo", 18.00);
		Produto p8 = new Produto(null, "Carne seca com catupiry", 19.00);
		Produto p9 = new Produto(null, "Camarão, queijo e palmito", 23.00);
		Produto p10 = new Produto(null, "Crepeburguer", 15.00);
		
		Produto p11 = new Produto(null, "Nutella com banana", 13.00);
		Produto p12 = new Produto(null, "Nutella com morango e banana", 15.00);
		Produto p13 = new Produto(null, "Nutella com morango", 16.00);
		Produto p14 = new Produto(null, "Chocolate com banana", 12.00);
		Produto p15 = new Produto(null, "Chocolate com morango e banana", 13.00);
		Produto p16 = new Produto(null, "Chocolate com morango", 10.00);
		Produto p17 = new Produto(null, "Produto 17", 10.00);
		Produto p18 = new Produto(null, "Produto 18", 10.00);
		Produto p19 = new Produto(null, "Produto 19", 10.00);
		Produto p20 = new Produto(null, "Produto 20", 10.00);
		Produto p21 = new Produto(null, "Produto 21", 10.00);
		Produto p22 = new Produto(null, "Produto 22", 10.00);
		Produto p23 = new Produto(null, "Produto 23", 10.00);
		Produto p24 = new Produto(null, "Produto 24", 10.00);
		Produto p25 = new Produto(null, "Produto 25", 10.00);
		Produto p26 = new Produto(null, "Produto 26", 10.00);
		Produto p27 = new Produto(null, "Produto 27", 10.00);
		Produto p28 = new Produto(null, "Produto 28", 10.00);
		Produto p29 = new Produto(null, "Produto 29", 10.00);
		Produto p30 = new Produto(null, "Produto 30", 10.00);
		Produto p31 = new Produto(null, "Produto 31", 10.00);
		Produto p32 = new Produto(null, "Produto 32", 10.00);
		Produto p33 = new Produto(null, "Produto 33", 10.00);
		Produto p34 = new Produto(null, "Produto 34", 10.00);
		Produto p35 = new Produto(null, "Produto 35", 10.00);
		Produto p36 = new Produto(null, "Produto 36", 10.00);
		Produto p37 = new Produto(null, "Produto 37", 10.00);
		Produto p38 = new Produto(null, "Produto 38", 10.00);
		Produto p39 = new Produto(null, "Produto 39", 10.00);
		Produto p40 = new Produto(null, "Produto 40", 10.00);
		Produto p41 = new Produto(null, "Produto 41", 10.00);
		Produto p42 = new Produto(null, "Produto 42", 10.00);
		Produto p43 = new Produto(null, "Produto 43", 10.00);
		Produto p44 = new Produto(null, "Produto 44", 10.00);
		Produto p45 = new Produto(null, "Produto 45", 10.00);
		Produto p46 = new Produto(null, "Produto 46", 10.00);
		Produto p47 = new Produto(null, "Produto 47", 10.00);
		Produto p48 = new Produto(null, "Produto 48", 10.00);
		Produto p49 = new Produto(null, "Produto 49", 10.00);
		Produto p50 = new Produto(null, "Produto 50", 10.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10));
		
		cat2.getProdutos().addAll(Arrays.asList(p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, 
				p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38, p39, p40, p41, p42, p43, p44, p45, p46, p47,
				p48, p49, p50));

		p1.getCategorias().add(cat1);
		p2.getCategorias().add(cat1);
		p3.getCategorias().add(cat1);
		p4.getCategorias().add(cat1);
		p5.getCategorias().add(cat1);
		p6.getCategorias().add(cat1);
		p7.getCategorias().add(cat1);
		p8.getCategorias().add(cat1);
		p9.getCategorias().add(cat1);
		p10.getCategorias().add(cat1);
		
		p12.getCategorias().add(cat2);
		p13.getCategorias().add(cat2);
		p14.getCategorias().add(cat2);
		p15.getCategorias().add(cat2);
		p16.getCategorias().add(cat2);
		p17.getCategorias().add(cat2);
		p18.getCategorias().add(cat2);
		p19.getCategorias().add(cat2);
		p20.getCategorias().add(cat2);
		p21.getCategorias().add(cat2);
		p22.getCategorias().add(cat2);
		p23.getCategorias().add(cat2);
		p24.getCategorias().add(cat2);
		p25.getCategorias().add(cat2);
		p26.getCategorias().add(cat2);
		p27.getCategorias().add(cat2);
		p28.getCategorias().add(cat2);
		p29.getCategorias().add(cat2);
		p30.getCategorias().add(cat2);
		p31.getCategorias().add(cat2);
		p32.getCategorias().add(cat2);
		p33.getCategorias().add(cat2);
		p34.getCategorias().add(cat2);
		p35.getCategorias().add(cat2);
		p36.getCategorias().add(cat2);
		p37.getCategorias().add(cat2);
		p38.getCategorias().add(cat2);
		p39.getCategorias().add(cat2);
		p40.getCategorias().add(cat2);
		p41.getCategorias().add(cat2);
		p42.getCategorias().add(cat2);
		p43.getCategorias().add(cat2);
		p44.getCategorias().add(cat2);
		p45.getCategorias().add(cat2);
		p46.getCategorias().add(cat2);
		p47.getCategorias().add(cat2);
		p48.getCategorias().add(cat2);
		p49.getCategorias().add(cat2);
		p50.getCategorias().add(cat2);
		
				
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		produtoRepository.saveAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
				p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38,
				p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

		Cliente cli1 = new Cliente(null, "Lucca Carlini", "lucca@gmail.com", "36378912377", TipoCliente.PESSOAFISICA, pe.encode("123"));
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Cliente cli2 = new Cliente(null, "Pedro Carlini", "pedro@gmail.com", "31628382740", TipoCliente.PESSOAFISICA, pe.encode("123"));
		cli2.getTelefones().addAll(Arrays.asList("93883321", "34252625"));
		cli2.addPerfil(Perfil.ADMIN);

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		Endereco e3 = new Endereco(null, "Avenida Floriano", "2106", null, "Centro", "281777012", cli2, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

	}
	
}
