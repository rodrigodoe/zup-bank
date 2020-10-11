package br.com.rodrigodoe.zupbank.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.rodrigodoe.zupbank.services.api.AmazonClient;


@RestController
@RequestMapping("/storage")
public class BucketController {

	private AmazonClient amazonClient;

	@Autowired
	public BucketController(AmazonClient amazonClient) {
		this.amazonClient = amazonClient;
	}

	@PostMapping("/uploadfile")
	public String uploadFile(@RequestPart(value = "file") MultipartFile file) throws IOException {
		return this.amazonClient.uploadFile(file);
	}

	@PostMapping("/deletefile")
	public String deleteFile(@RequestPart(value = "url") String fileUrl) {
		return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
	}
}
