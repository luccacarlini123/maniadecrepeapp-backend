package com.mouzetech.maniadecrepeapp.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.mouzetech.maniadecrepeapp.services.exception.FileException;

@Service
public class S3Service {

	@Autowired
	private AmazonS3 s3Client;

	@Value("${s3.bucket}")
	private String bucketName;

	public URI uploadFile(MultipartFile file) {
		try {
			String contentType = file.getContentType();
			InputStream is = file.getInputStream();
			String fileName = file.getOriginalFilename();
			return uploadFile(fileName, is, contentType);
		} catch (IOException e) {
			throw new FileException("Erro de IO: " + e.getMessage());
		}
		
	}

	public URI uploadFile(String fileName, InputStream is, String contentType) {
		try {
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(contentType);
			s3Client.putObject(bucketName, fileName, is, meta);
			return s3Client.getUrl(bucketName, fileName).toURI();
		} catch (URISyntaxException e) {
			throw new FileException("Erro ao converter URL para URI");
		}
	}
	
	public void deleteFile(String fileName) {
		s3Client.deleteObject(bucketName, fileName);
	}
}