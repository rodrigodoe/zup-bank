package br.com.rodrigodoe.zupbank.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.rodrigodoe.zupbank.data.dtos.FileStorageDTO;
import br.com.rodrigodoe.zupbank.services.FileStoreService;
import br.com.rodrigodoe.zupbank.services.api.S3Services;
import br.com.rodrigodoe.zupbank.utils.FileStorageHateoasUtils;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/clients/{clientId}/files")
public class BucketController {

	@Autowired
	S3Services s3Services;

	@Autowired
	FileStoreService fileStoreService;

	@PostMapping
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable Long clientId)
			throws IOException {

		FileStorageDTO dto = fileStoreService.save(clientId, file);
		return ResponseEntity.created(URI.create("/clients/" + clientId + "/file/download/" + dto.getFilename()))
				.body(dto);

	}

	@GetMapping("/{keyname}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable("clientId") Long clientId, @PathVariable String keyname) {
		
		ByteArrayOutputStream downloadInputStream = fileStoreService.findByFileName(clientId, keyname);
		

		return ResponseEntity.ok().contentType(contentType(keyname))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + keyname + "\"")
				.body(downloadInputStream.toByteArray());
	}

	@GetMapping
	@ApiOperation(value = "find")
	public FileStorageDTO find(@PathVariable("clientId") Long clientId) {
		FileStorageDTO dto = fileStoreService.findByClientId(clientId);
		FileStorageHateoasUtils.create(dto);
		return dto;
	}

	private MediaType contentType(String keyname) {
		String[] arr = keyname.split("\\.");
		String type = arr[arr.length - 1];
		switch (type) {
		case "txt":
			return MediaType.TEXT_PLAIN;
		case "png":
			return MediaType.IMAGE_PNG;
		case "jpg":
			return MediaType.IMAGE_JPEG;
		default:
			return MediaType.APPLICATION_OCTET_STREAM;
		}
	}

}
