package br.com.rodrigodoe.zupbank.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rodrigodoe.zupbank.data.models.FileStorage;

@Repository
public interface FileStorageRepository extends JpaRepository<FileStorage, Long> {

	Optional<FileStorage> findByClientId(Long clientId);

}
