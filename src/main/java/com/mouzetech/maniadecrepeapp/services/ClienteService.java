package com.mouzetech.maniadecrepeapp.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.mouzetech.maniadecrepeapp.domain.Cidade;
import com.mouzetech.maniadecrepeapp.domain.Cliente;
import com.mouzetech.maniadecrepeapp.domain.Endereco;
import com.mouzetech.maniadecrepeapp.domain.enums.Perfil;
import com.mouzetech.maniadecrepeapp.domain.enums.TipoCliente;
import com.mouzetech.maniadecrepeapp.dto.ClienteDTO;
import com.mouzetech.maniadecrepeapp.dto.ClienteNewDTO;
import com.mouzetech.maniadecrepeapp.repositories.ClienteRepository;
import com.mouzetech.maniadecrepeapp.repositories.EnderecoRepository;
import com.mouzetech.maniadecrepeapp.security.UserSS;
import com.mouzetech.maniadecrepeapp.services.exception.AuthorizationException;
import com.mouzetech.maniadecrepeapp.services.exception.DataIntegrityException;
import com.mouzetech.maniadecrepeapp.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private S3Service s3Client;
	
	public Cliente buscarPorId(Integer id) {
		
		UserSS user = UserService.authenticated();
		
		if(user==null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		
		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> 
		new ObjectNotFoundException("Objeto não encontrado! Id: "+id+". Tipo: "+Cliente.class));
	}
	
	public Cliente editar(Cliente obj) {
		Cliente newObj = buscarPorId(obj.getId());
		updateData(newObj, obj);
		return clienteRepository.save(newObj);
	}
	
	@Transactional
	public Cliente inserir(Cliente obj) {
		obj.setId(null);
		obj = clienteRepository.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
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
			throw new DataIntegrityException("Não é possível excluir, há pedidos associados.");
		}
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null, null);
	}
	
	public URI uploadFile(MultipartFile file) {
		UserSS user = UserService.authenticated();
		if(user==null) {
			throw new AuthorizationException("Acesso negado");
		}
		URI uri = s3Client.uploadFile(file);
		Cliente cli = buscarPorId(user.getId());
		cli.setImageUrl(uri.toString());
		clienteRepository.save(cli);
		return uri;
	}
	
	public void deleteFile(String fileName) {
		s3Client.deleteFile(fileName);
	}
	
	public Cliente fromDTO(ClienteNewDTO obj) {
		Cliente cli = new Cliente(null, obj.getNome(), obj.getEmail(), obj.getCpfOuCnpj(), TipoCliente.toEnum(obj.getTipo()), pe.encode(obj.getSenha()));
		Cidade cid = new Cidade(obj.getCidadeId(), null, null);
		Endereco end = new Endereco(null, obj.getLogradouro(), obj.getNumero(), obj.getComplemento(), obj.getBairro(), obj.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(obj.getTelefone1());
		if(obj.getTelefone2()!=null) {
			cli.getTelefones().add(obj.getTelefone2());
		}
		if(obj.getTelefone3()!=null) {
			cli.getTelefones().add(obj.getTelefone3());
		}
		return cli;
	}
}