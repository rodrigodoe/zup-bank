package br.com.rodrigodoe.zupbank.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import br.com.rodrigodoe.zupbank.data.dtos.AddressDTO;
import br.com.rodrigodoe.zupbank.data.models.Address;

public class AddressConverterUtils {
	
	static ModelMapper modelMapper = new ModelMapper();
	
	public static  AddressDTO convertToDto(Address address) {
		AddressDTO addressDto = modelMapper.map(address, AddressDTO.class);
	    return addressDto;
	}
	
	public static  Address convertToEntity(AddressDTO addressDto) {
		Address address = modelMapper.map(addressDto, Address.class);
	    return address;
	}
	
	
	public static <S, T> List<T> convertList(List<S> source, Class<T> targetClass) {
	    return source
	      .stream()
	      .map(element -> modelMapper.map(element, targetClass))
	      .collect(Collectors.toList());
	}

}
