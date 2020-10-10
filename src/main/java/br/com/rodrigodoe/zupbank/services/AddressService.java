package br.com.rodrigodoe.zupbank.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.rodrigodoe.zupbank.data.dtos.AddressDTO;
import br.com.rodrigodoe.zupbank.data.dtos.ClientDTO;
import br.com.rodrigodoe.zupbank.data.models.Address;
import br.com.rodrigodoe.zupbank.data.models.Client;
import br.com.rodrigodoe.zupbank.repositories.AddressRepository;
import br.com.rodrigodoe.zupbank.repositories.ClientRepository;
import br.com.rodrigodoe.zupbank.utils.AddressConverterUtils;
import br.com.rodrigodoe.zupbank.utils.ClientConverterUtils;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;
	

	public AddressDTO create(AddressDTO addressDTO) {
		
		var addressEntity = AddressConverterUtils.convertToEntity(addressDTO);
		
		var newAdressEntity = this.addressRepository.save(addressEntity);
		
		return AddressConverterUtils.convertToDto(newAdressEntity);
	}

	public AddressDTO findById(Long id) {
		var address = this.addressRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum endereco encontrado"));

		AddressDTO addressDto = AddressConverterUtils.convertToDto(address);
		return addressDto;
	}

	public List<AddressDTO> findAll() {
		List<Address> address = this.addressRepository.findAll();
		return ClientConverterUtils.convertList(address, AddressDTO.class);
	}

}
