package br.com.rodrigodoe.zupbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rodrigodoe.zupbank.data.model.Client;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	
	
	Client findByEmail(String email);

}
