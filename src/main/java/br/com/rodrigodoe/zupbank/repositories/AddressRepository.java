package br.com.rodrigodoe.zupbank.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.rodrigodoe.zupbank.data.models.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
	

}
