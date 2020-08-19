package com.mouzetech.maniadecrepeapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mouzetech.maniadecrepeapp.services.S3Service;

@SpringBootApplication
public class ManiadecrepeappBackendApplication implements CommandLineRunner {

	@Autowired
	private S3Service s3Client;
	
	public static void main(String[] args) {
		SpringApplication.run(ManiadecrepeappBackendApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		s3Client.uploadFile("C:\\Users\\Ratinho\\Desktop\\Creperia\\emoji1.jpg");		
	}
}