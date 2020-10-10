package br.com.rodrigodoe.zupbank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rodrigodoe.zupbank.data.models.Client;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	
	
	Client findByEmail(String email);

}
