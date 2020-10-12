package br.com.rodrigodoe.zupbank.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.rodrigodoe.zupbank.data.dtos.FileStorageDTO;
import br.com.rodrigodoe.zupbank.data.models.Client;
import br.com.rodrigodoe.zupbank.data.models.FileStorage;
import br.com.rodrigodoe.zupbank.repositories.ClientRepository;
import br.com.rodrigodoe.zupbank.repositories.FileStorageRepository;
import br.com.rodrigodoe.zupbank.services.api.S3Services;
import br.com.rodrigodoe.zupbank.utils.ClassConverterUtils;

@Service
public class FileStoreService {

	@Autowired
	FileStorageRepository fileStorageRepository;

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	S3Services s3Services;

	public FileStorageDTO save(Long clientId, MultipartFile file) throws IOException {

		Client client = clientRepository.findById(clientId)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente nao cadastro no banco de dados"));

		try {
			String filename = s3Services.uploadFile(file);

			FileStorage fs = new FileStorage();
			Optional<FileStorage> opt = this.fileStorageRepository.findByClientId(clientId);
			if (opt.isPresent()) {
				s3Services.deleteFile(opt.get().getFilename());
				fs.setId(opt.get().getId());

			}

			fs.setClient(client);
			fs.setFilename(filename);
			FileStorageDTO dto = ClassConverterUtils.convert(fileStorageRepository.save(fs), FileStorageDTO.class);
			return dto;

		} catch (IOException e) {
			throw new IOException(e);
		}

	}

	public FileStorageDTO findByClientId(Long clientId) {
		FileStorage file = this.fileStorageRepository.findByClientId(clientId)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum arquivo encontrado"));
		return ClassConverterUtils.convert(file, FileStorageDTO.class);
	}

	public ByteArrayOutputStream findByFileName(Long clientId, String filename) {

		clientRepository.findById(clientId)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente nao cadastro no banco de dados"));

		this.fileStorageRepository.findByFilename(filename)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum arquivo encontrado"));

		ByteArrayOutputStream downloadInputStream = s3Services.downloadFile(filename);

		return downloadInputStream;
	}

}
