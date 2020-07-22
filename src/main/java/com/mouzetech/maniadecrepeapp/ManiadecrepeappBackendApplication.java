package com.mouzetech.maniadecrepeapp;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mouzetech.maniadecrepeapp.domain.Categoria;
import com.mouzetech.maniadecrepeapp.repositories.CategoriaRepository;

@SpringBootApplication
public class ManiadecrepeappBackendApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(ManiadecrepeappBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		categoriaRepo.saveAll(Arrays.asList(cat1, cat2));
	}

}
