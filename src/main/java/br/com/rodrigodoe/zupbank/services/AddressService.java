package br.com.rodrigodoe.zupbank.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rodrigodoe.zupbank.data.dtos.AddressDTO;
import br.com.rodrigodoe.zupbank.data.dtos.ClientDTO;
import br.com.rodrigodoe.zupbank.data.models.Address;
import br.com.rodrigodoe.zupbank.data.models.Client;
import br.com.rodrigodoe.zupbank.repositories.ClientRepository;
import br.com.rodrigodoe.zupbank.utils.AddressConverterUtils;
import br.com.rodrigodoe.zupbank.utils.ClientConverterUtils;

@Service
public class AddressService {
	
	@Autowired
	ClientRepository clientRepository;
	
	public AddressDTO create(ClientDTO clientDto, AddressDTO addressDto) {
		
		Address address = AddressConverterUtils.convertToEntity(addressDto);
		
		
		clientDto.setAddress(address);
		
		Client clientEntity = ClientConverterUtils.convertToEntity(clientDto);
		
		Client updatedClient = clientRepository.save(clientEntity);
		
		
		AddressDTO newAddressDto = AddressConverterUtils.convertToDto(updatedClient.getAddress());
		
		
		return  newAddressDto;
	}

}
