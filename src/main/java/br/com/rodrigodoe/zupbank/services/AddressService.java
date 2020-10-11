package br.com.rodrigodoe.zupbank.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.rodrigodoe.zupbank.data.dtos.AddressDTO;
import br.com.rodrigodoe.zupbank.data.models.Address;
import br.com.rodrigodoe.zupbank.data.models.Client;
import br.com.rodrigodoe.zupbank.exceptions.DuplicateConstraintException;
import br.com.rodrigodoe.zupbank.repositories.AddressRepository;
import br.com.rodrigodoe.zupbank.repositories.ClientRepository;
import br.com.rodrigodoe.zupbank.utils.ClassConverterUtils;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private ClientRepository clientRepository;

	public AddressDTO create(Long clientId, AddressDTO addressDTO) {

		var addressEntity = ClassConverterUtils.convert(addressDTO, Address.class);

		clientRepository.findById(clientId)
				.orElseThrow(() -> new ResourceNotFoundException("Cliente nao cadastro no banco de dados"));

		var foundEntity = addressRepository.findByClientId(clientId);

		if (foundEntity.isPresent()) {
			throw new DuplicateConstraintException("Endereço ja cadastro para o cliente no banco de dados");
		}

		var client = new Client();
		client.setId(clientId);
		addressEntity.setClient(client);

		var newAdressEntity = this.addressRepository.save(addressEntity);

		return ClassConverterUtils.convert(newAdressEntity, AddressDTO.class);
	}

	public AddressDTO findByClientId(Long clientId) {
		
		var address = this.addressRepository.findByClientId(clientId)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum endereco encontrado"));
		return ClassConverterUtils.convert(address, AddressDTO.class);
	}



	public AddressDTO update(Long clientId, AddressDTO adressDto) {
		Address address = this.addressRepository.findByClientId(clientId)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum cliente/endereco encontrado"));
		
		Address addressEntity = ClassConverterUtils.convert(adressDto, Address.class);
		
		
		
		address.setAddressLine1(addressEntity.getAddressLine1());
		address.setAddressLine2(addressEntity.getAddressLine2());
		address.setCity(addressEntity.getCity());
		address.setState(addressEntity.getState());
		address.setSuburb(addressEntity.getSuburb());
		address.setZipcode(addressEntity.getZipcode());
		var client = new Client();
		client.setId(clientId);
		address.setClient(client);
		
		Address  entity = this.addressRepository.save(address);
		

		return ClassConverterUtils.convert(entity, AddressDTO.class);
		
	}

}
